package io.github.przbetkier.tuscan.adapter.api.response;

import io.github.przbetkier.tuscan.adapter.api.response.dto.BanInfo;
import io.github.przbetkier.tuscan.adapter.api.response.dto.GameDetails;
import io.github.przbetkier.tuscan.client.player.Membership;

public class PlayerDetailsResponse {

    private final String playerId;
    private final String nickname;
    private final GameDetails gameDetails;
    private final String avatarUrl;
    private final String country;
    private final Membership membership;
    private final BanInfo ban;

    public PlayerDetailsResponse(String playerId, String nickname, GameDetails gameDetails, String avatarUrl,
                                 String country, Membership membership, BanInfo ban) {
        this.playerId = playerId;
        this.nickname = nickname;
        this.gameDetails = gameDetails;
        this.avatarUrl = avatarUrl;
        this.country = country;
        this.membership = membership;
        this.ban = ban;
    }

    public String getPlayerId() {
        return playerId;
    }

    public String getNickname() {
        return nickname;
    }

    public GameDetails getGameDetails() {
        return gameDetails;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getCountry() {
        return country;
    }

    public Membership getMembership() {
        return membership;
    }

    public BanInfo getBan() {
        return ban;
    }
}
