package io.github.przbetkier.tuscan.domain.player.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MatchHistoryDto {

    private String elo;
    private String matchId;
    private long date;
    private String mode;

    @JsonCreator
    public MatchHistoryDto(@JsonProperty("elo") String elo,
                           @JsonProperty("matchId") String matchId,
                           @JsonProperty("date") long date,
                           @JsonProperty("gameMode") String mode) {
        this.elo = elo;
        this.matchId = matchId;
        this.date = date;
        this.mode = mode;
    }

    public String getElo() {
        return elo;
    }

    public String getMatchId() {
        return matchId;
    }

    public long getDate() {
        return date;
    }

    public String getMode() {
        return mode;
    }

    public boolean hasElo() {
        return getElo() != null;
    }
}