package integration.common.response

import groovy.transform.CompileStatic
import io.github.przbetkier.tuscan.client.player.Ban
import io.github.przbetkier.tuscan.client.player.PlayerDetails

import java.time.format.DateTimeFormatter

@CompileStatic
class PlayerDetailsResponse {

    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss zzz yyyy")

    static def String simple(PlayerDetails playerDetails) {
        def bans = getBans(playerDetails.getBans())
        """{
            "player_id": "$playerDetails.playerId",
            "nickname": "$playerDetails.nickname",
            "avatar": "",
            "country": "pl",
            "cover_image": "https://image.com/12345.png",
            "cover_featured_image": "https://image.com/image",
            "infractions": {
            "last_infraction_date": "Fri Sep 01 23:34:09 UTC 2017",
            "afk": 0,
            "leaver": 0,
            "qm_not_checkedin": 1,
            "qm_not_voted": 0
        },
            "platforms": {
            "steam": "STEAM_1:0:937261"
        },
            "games": {
            "csgo": {
                "game_profile_id": "$playerDetails.games.csgo.steamId",
                "region": "EU",
                "regions": {
                    "EU": {
                        "selected_ladder_id": "a7c7b0a6-365e-49cc-8aa3-8c29af2dea60"
                    }
                },
                "skill_level_label": "10",
                "game_player_id": "$playerDetails.games.csgo.steamId",
                "skill_level": 10,
                "faceit_elo": 2702,
                "game_player_name": "player"
            }
        },
            "settings": {
            "language": "en"
        },
            "friends_ids": [
                "425a97f6-2a9b-426d-a81e-0cd63ba2f343",
                "8854e907-2f15-40c3-ac40-2eb84bb03a71"
        ],
            "bans": $bans,
            "new_steam_id": "[U:1:460206]",
            "steam_id_64": "3242343242",
            "steam_nickname": "playerId",
            "membership_type": "premium",
            "memberships": [
                "free"
        ],
            "faceit_url": "https://www.faceit.com/{lang}/players/$playerDetails.nickname"
        }"""
    }

    static String getBans(List<Ban> bans) {
        if (bans.isEmpty()) return "[]"
        return """[{
                "created_at": "${bans[0].createdAt.format(formatter)}",
                "guid": "8886172e-bb57-40d3-8b9a-15f3e065ee49",
                "nickname": "Shra1k",
                "reason": "${bans.first().reason}",
                "reason_type": "cheating",
                "starts_at": "${bans[0].startsAt.format(formatter)}",
                "type": "login"
            }]"""

    }
}
