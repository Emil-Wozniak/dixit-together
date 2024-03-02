package pl.ejdev.dixittogether.features.game.view.service

import androidx.compose.ui.graphics.Color
import pl.ejdev.dixittogether.features.game.domain.entities.PlayerResult

internal fun roundService(
    results: List<PlayerResult>,
    cardsVotes: Map<Color, List<Color>>
): List<PlayerResult> {
    val players = results.map(PlayerResult::player)
    val narratorColor = players.find { it.narrator }!!.gameColor!!.color
    val allPlayersVotedNarratorCard = cardsVotes.entries.all { it.key == narratorColor }
    return if (allPlayersVotedNarratorCard) {
        results.map {
            if (it.player.narrator) {
                it
            } else {
                it.apply { score += 2 }
            }
        }
    } else {
        results.map {
            val color = it.player.gameColor!!.color
            val points = cardsVotes.entries.find { it.key == color }?.run { value.count() * 3 } ?: 0
            it.apply { score += points }
        }
    }
}