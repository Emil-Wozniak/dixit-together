package pl.ejdev.dixittogether.features.players.domain.entities

import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import pl.ejdev.dixittogether.features.core.shared.GameColor

data class Player(
    val name: String? = null,
    val color: GameColor? = null
)

internal val playerSaver: Saver<Player, Any> = listSaver(
    save = { listOf(it.name, it.color) },
    restore = { Player(it[0] as String?, it[1] as GameColor?) }
)