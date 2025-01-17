package io.github.przbetkier.tuscan.common.response

import groovy.transform.CompileStatic
import io.github.przbetkier.tuscan.adapter.api.response.MatchFullDetailsResponse
import io.github.przbetkier.tuscan.adapter.api.response.dto.MatchResult
import io.github.przbetkier.tuscan.adapter.api.response.dto.Player
import io.github.przbetkier.tuscan.adapter.api.response.dto.Team
import io.github.przbetkier.tuscan.adapter.api.response.dto.TeamStats
import io.github.przbetkier.tuscan.common.SamplePlayer

@CompileStatic
class SampleMatchFullDetailsResponse {

    static def simple() {
        Player playerOne = SamplePlayer.simple()
        Player playerTwo = SamplePlayer.simple()

        return new MatchFullDetailsResponse(
                "matchId-1",
                "de_inferno",
                "16/7",
                23,
                [
                        new Team(
                                "team-1",
                                new TeamStats(
                                        "team-One",
                                        3.33d),
                                [playerOne, playerTwo].toSet()),
                        new Team(
                                "team-1",
                                new TeamStats(
                                        "team-One",
                                        3.33d),
                                [playerOne, playerTwo].toSet()),
                ],
                "team-1",
                MatchResult.WIN,
                "url-1"
        )
    }
}
