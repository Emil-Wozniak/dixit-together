package pl.ejdev.dixittogether.features.game.domain.entities

import androidx.compose.ui.graphics.Color
import pl.ejdev.dixittogether.features.players.domain.entities.Player

internal data class PlayerResult(
    val player: Player,
    var score: Int = 0,
)

internal fun PlayerResult.toPlayerDetails(): PlayerDetails =
    PlayerDetails(
        name = requireNotNull(value = this.player.name),
        color = this.player.gameColor?.color ?: Color.LightGray,
        score = this.score
    )
