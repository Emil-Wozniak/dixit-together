package pl.ejdev.dixittogether.features.players.domain.entities

import pl.ejdev.dixittogether.features.core.shared.GameColor

data class Player(
    val name: String? = null,
    val color: GameColor? = null
)
