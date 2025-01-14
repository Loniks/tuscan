package io.github.przbetkier.tuscan.adapter.api;

import io.github.przbetkier.tuscan.adapter.api.response.PlayerCsgoStatsResponse;
import io.github.przbetkier.tuscan.adapter.api.response.PlayerDetailsResponse;
import io.github.przbetkier.tuscan.domain.player.PlayerService;
import io.micrometer.core.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/faceit/players/details")
@Timed("endpoint.playerDetails")
class PlayerDetailsEndpoint {

    private static final Logger logger = LoggerFactory.getLogger(PlayerDetailsEndpoint.class);

    private final PlayerService playerService;

    PlayerDetailsEndpoint(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    public Mono<PlayerDetailsResponse> getPlayerDetails(@RequestParam String nickname) {
        logger.info("Player details requested for [{}].", nickname);
        return playerService.getPlayerDetails(nickname);
    }

    @GetMapping("/csgo/{playerId}")
    public Mono<PlayerCsgoStatsResponse> getCsgoStats(@PathVariable String playerId) {
        return playerService.getCsgoStats(playerId);
    }
}
