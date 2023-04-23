package pl.ejdev.dixittogether.features.game.domain.entities

import androidx.compose.ui.graphics.Color
import pl.ejdev.dixittogether.features.players.domain.entities.Player

data class PlayerResult(
    val player: Player,
    var score: Int,
)

fun PlayerResult.toPlayerDetails(): PlayerDetails =
    PlayerDetails(
        name = requireNotNull(value = this.player.name),
        color = this.player.gameColor?.color ?: Color.Black,
        score = this.score
    )

fun PlayerResult.incrementScore() = apply { this.score += 1 }