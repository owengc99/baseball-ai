package com.owengc.baseball_ai.service;

import com.owengc.baseball_ai.dto.FranchiseSummary;
import com.owengc.baseball_ai.dto.TeamDetail;
import com.owengc.baseball_ai.dto.TeamSummary;
import com.owengc.baseball_ai.entity.Team;
import com.owengc.baseball_ai.entity.TeamYearId;
import com.owengc.baseball_ai.exception.TeamNotFoundException;
import com.owengc.baseball_ai.repository.TeamRepository;
import org.springframework.stereotype.Service;
import static com.owengc.baseball_ai.util.StringUtils.blankToNull;

import java.util.List;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public List<TeamSummary> getSeasons(String teamId) {
        List<Team> seasons = teamRepository.findByIdTeamIdOrderByIdYearId(teamId);
        if (seasons.isEmpty()) throw new TeamNotFoundException(teamId);

        return seasons.stream().map(teamSeason ->
                new TeamSummary(
                        teamSeason.getId().getTeamId(),
                        teamSeason.getId().getYearId(),
                        teamSeason.getName(),
                        teamSeason.getW(),
                        teamSeason.getL(),
                        teamSeason.getRank(),
                        blankToNull(teamSeason.getDivWin()),
                        blankToNull(teamSeason.getWcWin()),
                        blankToNull(teamSeason.getLgWin()),
                        blankToNull(teamSeason.getWsWin())
                )
        ).toList();

    }

    public TeamDetail getTeam(String teamId, Integer yearId) {
        return teamRepository.findById(new TeamYearId(teamId, yearId))
                .map(this::toDetail)
                .orElseThrow(() -> new TeamNotFoundException(teamId, yearId));
    }

    private TeamDetail toDetail(Team team) {
        return new TeamDetail(
                team.getId().getTeamId(),
                team.getId().getYearId(),
                team.getName(),
                new TeamDetail.Identity(
                        team.getLeagueId(),
                        blankToNull(team.getDivId()),
                        team.getFranchId()
                ),
                new TeamDetail.Record(
                        team.getG(),
                        team.getGHome(),
                        team.getW(),
                        team.getL(),
                        team.getRank(),
                        blankToNull(team.getDivWin()),
                        blankToNull(team.getWcWin()),
                        blankToNull(team.getLgWin()),
                        blankToNull(team.getWsWin())
                ),
                new TeamDetail.Batting(
                        team.getR(),
                        team.getAb(),
                        team.getH(),
                        team.getDoubles(),
                        team.getTriples(),
                        team.getHr(),
                        team.getBb(),
                        team.getSo(),
                        team.getSb(),
                        team.getCs(),
                        team.getHbp(),
                        team.getSf()
                ),
                new TeamDetail.Pitching(
                        team.getRa(),
                        team.getEr(),
                        team.getEra(),
                        team.getCg(),
                        team.getSho(),
                        team.getSv(),
                        team.getIpOuts(),
                        team.getHa(),
                        team.getHra(),
                        team.getBba(),
                        team.getSoa()
                ),
                new TeamDetail.Fielding(
                        team.getE(),
                        team.getDp(),
                        team.getFp()
                ),
                new TeamDetail.Venue(
                        team.getPark(),
                        team.getAttendance(),
                        team.getBpf(),
                        team.getPpf()
                )
        );
    }

    public List<FranchiseSummary> getFranchises() {
        return teamRepository.findFranchises();
    }

}
