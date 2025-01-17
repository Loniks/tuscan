package io.github.przbetkier.tuscan.domain.player

import io.github.przbetkier.tuscan.client.player.MatchHistoryDto
import io.github.przbetkier.tuscan.client.player.PlayerHistoryDto
import spock.lang.Specification

import java.time.Instant
import java.time.LocalDateTime

class PlayerHistoryMapperTest extends Specification {

    def "should map dto with 120 matches to last 100 matches player history response"() {
        given:
        def listOf50MatchesHistory = generateMatchHistoryDto(1200, 120)
        def playerHistoryDto = new PlayerHistoryDto(listOf50MatchesHistory)

        when:
        def result = PlayerHistoryMapper.map(playerHistoryDto.matchHistoryDtoList)

        then:
        result.matchHistory.size() == 100
        result.matchHistory[0].date == Instant.ofEpochMilli(listOf50MatchesHistory[0].date)
        result.matchHistory[0].eloDiff == result.matchHistory[0].elo - result.matchHistory[1].elo
        result.matchHistory[1].eloDiff == result.matchHistory[1].elo - result.matchHistory[2].elo
        result.matchHistory[19].eloDiff == result.matchHistory[19].elo - Integer.valueOf(listOf50MatchesHistory.get(20).elo)
    }

    def "should map dto with 120 matches to last 100 without elo"() {
        given:
        def listOf50MatchesHistory = generateMatchHistoryDto(null, 120)
        def playerHistoryDto = new PlayerHistoryDto(listOf50MatchesHistory)

        when:
        def result = PlayerHistoryMapper.map(playerHistoryDto.matchHistoryDtoList)

        then:
        result.matchHistory.size() == 100
        for (int i = 0; i < result.matchHistory.size(); i++) {
            result.matchHistory[i].elo == 0 && result.matchHistory[i].eloDiff == 0
        }
    }

    def "should map dto with less than 100 matches to last matches history"() {
        given:
        def matchesList = generateMatchHistoryDto(1025, 99)
        def playerHistoryDto = new PlayerHistoryDto(matchesList)

        when:
        def result = PlayerHistoryMapper.map(playerHistoryDto.matchHistoryDtoList)

        then:
        result.matchHistory.size() == 99
        result.matchHistory[matchesList.size() - 1].eloDiff == 25
    }

    def "should map properly map history with diffs when one has no elo"() {
        given:
        def match1 = matchWithElo("1050", 1)
        def match2 = matchWithElo(null, 2)
        def match3 = matchWithElo("1000", 3)
        def matchesList = [match1, match2, match3]
        def playerHistoryDto = new PlayerHistoryDto(matchesList)

        when:
        def result = PlayerHistoryMapper.map(playerHistoryDto.matchHistoryDtoList)

        then:
        result.matchHistory.size() == 3
        result.matchHistory[0].eloDiff == 50
        result.matchHistory[0].elo == 1050
        result.matchHistory[1].eloDiff == 0
        result.matchHistory[1].elo == 1000
        result.matchHistory[2].eloDiff == 0
        result.matchHistory[2].elo == 1000
    }

    def "should map properly with staring elo points when history is small"() {
        given:
        def match1 = matchWithElo("1100", 1)
        def match2 = matchWithElo("1050", 2)
        def match3 = matchWithElo(null, 3)
        def matchesList = [match1, match2, match3]
        def playerHistoryDto = new PlayerHistoryDto(matchesList)

        when:
        def result = PlayerHistoryMapper.map(playerHistoryDto.matchHistoryDtoList)

        then:
        result.matchHistory.size() == 3
        result.matchHistory[0].eloDiff == 50
        result.matchHistory[0].elo == 1100
        result.matchHistory[1].eloDiff == 50
        result.matchHistory[1].elo == 1050
        result.matchHistory[2].eloDiff == 0
        result.matchHistory[2].elo == PlayerHistoryMapper.STARTING_ELO_POINTS
    }

    def "should map to empty list when history has no matches"() {
        given:
        def playerHistoryDto = new PlayerHistoryDto([])

        when:
        def result = PlayerHistoryMapper.map(playerHistoryDto.matchHistoryDtoList)

        then:
        noExceptionThrown()
        result.matchHistory == []
    }

    static generateMatchHistoryDto(Integer elo, int matches) {
        List<MatchHistoryDto> matchesList = new ArrayList<>()

        def eloPoints

        if (elo != null) {
            eloPoints = elo.toString()
        } else {
            eloPoints = null
        }

        for (int i = 0; i < matches; i++) {
            def match = matchWithElo(eloPoints, i)
            matchesList.add(match)
            if (elo) {
                elo++
            }
        }
        return matchesList
    }

    static MatchHistoryDto matchWithElo(String elo, int id) {
        new MatchHistoryDto(
                elo,
                "match-$id",
                12345,
                "5v5",
                "1.123",
                "80"
        )
    }
}
