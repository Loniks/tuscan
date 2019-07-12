package io.github.przbetkier.tuscan.domain.player;

import io.github.przbetkier.tuscan.adapter.api.response.PlayerHistoryResponse;
import io.github.przbetkier.tuscan.adapter.api.response.dto.MatchHistory;
import io.github.przbetkier.tuscan.client.player.MatchHistoryDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.Instant.ofEpochMilli;
import static java.time.LocalDateTime.ofInstant;
import static java.util.TimeZone.getDefault;

public class PlayerHistoryMapper {

    private static final Integer MAX_MATCHES_COUNT = 20;
    private static final Integer STARTING_ELO_POINTS = 1000;

    private PlayerHistoryMapper() {
    }

    public static PlayerHistoryResponse map(List<MatchHistoryDto> historyMatches) {

        historyMatches = filterHistory(historyMatches);

        List<MatchHistory> matchHistoryList = new ArrayList<>();
        int historySize = historyMatches.size();

        List<MatchHistoryDto> lastMatches = getLast20MatchesFromHistory(historyMatches, historySize);

        for (int i = 0; i < lastMatches.size() - 1; i++) {
            System.out.println("last matches size: " + lastMatches.size());
            System.out.println("Match " + (i+1));
            MatchHistoryDto matchAfter = lastMatches.get(i);
            MatchHistoryDto matchBefore = lastMatches.get(i + 1);

            Integer eloAfter;
            Integer eloBefore;

            if (matchAfter.hasElo() && matchBefore.hasElo()) {
                eloAfter = convertToElo(matchAfter.getElo());
                System.out.println(eloAfter);
                eloBefore = convertToElo(matchBefore.getElo());
                System.out.println(eloBefore);
            } else {
                eloAfter = 0;
                eloBefore = 0;
            }

            MatchHistory matchHistoryToAdd = new MatchHistory(
                    matchAfter.getMatchId(),
                    getLocalDateTimeFromTimestamp(matchAfter.getDate()),
                    eloAfter,
                    eloAfter - eloBefore,
                    new BigDecimal(matchAfter.getKdRatio()),
                    new Integer(matchAfter.getHsPercentage()));
            matchHistoryList.add(matchHistoryToAdd);
        }

        if (lastMatches.size() <= MAX_MATCHES_COUNT) {
            MatchHistoryDto firstMatch = historyMatches.get(historySize - 1);

            Integer eloAfter;
            Integer eloBefore;

            if(firstMatch.hasElo()) {
                eloAfter = convertToElo(firstMatch.getElo());
                eloBefore = STARTING_ELO_POINTS;
            } else {
                eloAfter = STARTING_ELO_POINTS;
                eloBefore = STARTING_ELO_POINTS;
            }

            matchHistoryList.add(new MatchHistory(
                    firstMatch.getMatchId(),
                    getLocalDateTimeFromTimestamp(firstMatch.getDate()),
                    eloAfter,
                    (eloAfter - eloBefore),
                    new BigDecimal(firstMatch.getKdRatio()),
                    new Integer(firstMatch.getHsPercentage()))
            );
        }
        return new PlayerHistoryResponse(matchHistoryList);
    }

    private static List<MatchHistoryDto> filterHistory(List<MatchHistoryDto> matches) {
        return matches.stream().filter(m -> m.getMode().equals("5v5")).collect(Collectors.toList());
    }

    private static List<MatchHistoryDto> getLast20MatchesFromHistory(List<MatchHistoryDto> matches, int historySize) {

        if (historySize > MAX_MATCHES_COUNT) {
            return new ArrayList<>(matches.subList(0, MAX_MATCHES_COUNT + 1));
        } else {
            return new ArrayList<>(matches.subList(0, historySize));
        }
    }

    private static Integer convertToElo(String eloString) {
        return Integer.valueOf(eloString.replace(",", ""));
    }

    private static LocalDateTime getLocalDateTimeFromTimestamp(long timestamp) {
        return ofInstant(ofEpochMilli(timestamp), getDefault().toZoneId());
    }
}
