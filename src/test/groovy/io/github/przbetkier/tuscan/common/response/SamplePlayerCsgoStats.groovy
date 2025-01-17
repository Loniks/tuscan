package io.github.przbetkier.tuscan.common.response

import groovy.transform.CompileStatic
import io.github.przbetkier.tuscan.adapter.api.response.PlayerCsgoStatsResponse
import io.github.przbetkier.tuscan.adapter.api.response.dto.MapStats
import io.github.przbetkier.tuscan.adapter.api.response.dto.OverallStats
import io.github.przbetkier.tuscan.adapter.api.response.dto.Performance
import io.github.przbetkier.tuscan.adapter.api.response.dto.SoloPerformance
import io.github.przbetkier.tuscan.adapter.api.response.dto.TeamPerformance
import io.github.przbetkier.tuscan.domain.CsgoMap

@CompileStatic
class SamplePlayerCsgoStats {

    static def simple() {
        return new PlayerCsgoStatsResponse(
                new OverallStats(
                        new BigDecimal(42),
                        new BigDecimal(1.32d),
                        21,
                        42,
                        new Performance(
                                new SoloPerformance(
                                        CsgoMap.DE_NUKE,
                                        1.33
                                ),
                                new TeamPerformance(
                                        CsgoMap.DE_TRAIN,
                                        90
                                ),
                                new SoloPerformance(
                                        CsgoMap.DE_INFERNO,
                                        0.99),
                                new TeamPerformance(
                                        CsgoMap.DE_NUKE,
                                        10
                                )
                        ),
                        1,
                        10
                ),
                [new MapStats(
                        CsgoMap.DE_NUKE,
                        3,
                        new BigDecimal(1.33d),
                        15,
                        10, 44, 14.1, 31, 5, 2
                ),
                 new MapStats(
                         CsgoMap.DE_TRAIN,
                         3,
                         new BigDecimal(1.11d), 100,
                         90, 33, 15.5, 66, 1, 2
                 ),
                 new MapStats(
                         CsgoMap.DE_INFERNO,
                         3,
                         new BigDecimal(0.99d), 5,
                         50, 44, 11.33, 2, 1, 0
                 )]
        )
    }
}
