package pl.ejdev.dixittogether.features.players.domain.entities

import androidx.compose.ui.graphics.Color
import pl.ejdev.dixittogether.features.core.shared.GameColor

internal data class Player(
    val name: String? = null,
    val gameColor: GameColor? = null,
    var narrator: Boolean = false
) {
    fun getColorOrDefault(): Color = gameColor?.color ?: Color.Black
    fun isNotNarrator() = !narrator
}
