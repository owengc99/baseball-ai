package com.owengc.baseball_ai.service;

import com.owengc.baseball_ai.dto.LeaderboardEntry;
import com.owengc.baseball_ai.enums.BattingStat;
import com.owengc.baseball_ai.enums.Span;
import com.owengc.baseball_ai.repository.LeaderboardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaderboardService {

    private final LeaderboardRepository leaderboardRepository;

    public LeaderboardService(LeaderboardRepository leaderboardRepository) {
        this.leaderboardRepository = leaderboardRepository;
    }
    public List<LeaderboardEntry> getBattingLeaders(BattingStat stat, Span span, int limit) {
        int cappedLimit = Math.min(limit, 100);

        return span == Span.SEASON
                ? leaderboardRepository.findBattingSeasonLeaders(stat, cappedLimit)
                : leaderboardRepository.findBattingCareerLeaders(stat, cappedLimit);
    }
}