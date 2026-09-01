package com.owengc.baseball_ai.repository;

import com.owengc.baseball_ai.dto.LeaderboardEntry;
import com.owengc.baseball_ai.enums.BattingStat;

import java.util.List;

public interface LeaderboardRepository {
    List<LeaderboardEntry> findBattingCareerLeaders(BattingStat stat, int limit);
    List<LeaderboardEntry> findBattingSeasonLeaders(BattingStat stat, int limit);
}