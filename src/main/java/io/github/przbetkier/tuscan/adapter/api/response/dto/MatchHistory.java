package io.github.przbetkier.tuscan.adapter.api.response.dto;

import java.math.BigDecimal;
import java.time.Instant;

public class MatchHistory {

    private final String matchId;
    private final Instant date;
    private final Integer elo;
    private final Integer eloDiff;
    private final BigDecimal kdRatio;
    private final Integer hsPercentage;

    public MatchHistory(String matchId, Instant date, Integer elo, Integer eloDiff, BigDecimal kdRatio,
                        Integer hsPercentage) {
        this.matchId = matchId;
        this.date = date;
        this.elo = elo;
        this.eloDiff = eloDiff;
        this.kdRatio = kdRatio;
        this.hsPercentage = hsPercentage;
    }

    public String getMatchId() {
        return matchId;
    }

    public Instant getDate() {
        return date;
    }

    public Integer getElo() {
        return elo;
    }

    public Integer getEloDiff() {
        return eloDiff;
    }

    public BigDecimal getKdRatio() {
        return kdRatio;
    }

    public Integer getHsPercentage() {
        return hsPercentage;
    }
}
