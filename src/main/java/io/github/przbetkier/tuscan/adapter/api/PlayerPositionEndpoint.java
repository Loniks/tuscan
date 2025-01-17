package io.github.przbetkier.tuscan.adapter.api;

import io.github.przbetkier.tuscan.adapter.api.response.PlayerPositionResponse;
import io.github.przbetkier.tuscan.domain.player.PlayerService;
import io.micrometer.core.annotation.Timed;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/faceit/player")
@Timed("endpoint.playerPosition")
class PlayerPositionEndpoint {

    private final PlayerService playerService;

    PlayerPositionEndpoint(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/position")
    public Mono<PlayerPositionResponse> getPlayerPosition(@RequestParam String playerId, @RequestParam String region,
                                                          @RequestParam String country) {
        return playerService.getPlayerPosition(playerId, region, country);
    }
}
