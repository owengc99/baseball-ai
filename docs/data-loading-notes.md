# Data Loading Notes

Notes on known data-quality issues and design decisions in the CSV loaders.

## Pitching: 2 rows skipped due to BAOpp overflow

Loading `Pitching.csv` skips 2 out of 57,628 rows due to numeric field overflow on `baopp`. Both are 1925 Negro Leagues records:

- `drakebi01` (Bill Drake), Kansas City Monarchs (NNL), BAOpp = 18.5
- `stronjo02` (Joe Strong), Baltimore Black Sox (ECL), BAOpp = 21.286

### Why these fail

`baopp` is defined as `NUMERIC(4,3)` in the schema, which allows values up to 9.999. Batting average against is mathematically bounded between 0.000 and 1.000, so `NUMERIC(4,3)` provides plenty of headroom for the domain.

The failing rows contain values that exceed 1.000, which suggests Lahman is repurposing the column for a different metric in Negro Leagues data — possibly hits per game or per inning. Whatever it is, it's not a batting average.

### Why we skip rather than widen the column

Widening `baopp` to accept these values would preserve the raw data but corrupt the column's semantic meaning. Any query aggregating BAOpp would then include values that aren't actually batting averages, silently biasing results.

The per-row exception handling in `PitchingLoaderService` catches the constraint violation, logs the failure, and continues with the remaining rows. The schema constraint stays as-is because it correctly encodes the domain rule that batting averages are 0-1.

Net impact: 2 rows lost out of ~57,600 (0.003%), both from a specific edge case in early Negro Leagues data. Any analytical query involving these players will simply have no BAOpp data for those specific stints — which is more honest than having a fabricated value.