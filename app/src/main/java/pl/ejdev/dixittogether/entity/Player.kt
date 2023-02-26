package pl.ejdev.dixittogether.entity

import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import pl.ejdev.dixittogether.shared.GameColor

internal data class Player(
    val name: String? = null,
    val color: GameColor? = null
)

internal val playerSaver: Saver<Player, Any> = listSaver(
    save = { listOf(it.name, it.color) },
    restore = { Player(it[0] as String?, it[1] as GameColor?) }
)