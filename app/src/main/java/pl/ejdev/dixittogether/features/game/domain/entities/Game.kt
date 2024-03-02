package pl.ejdev.dixittogether.features.game.domain.entities

import pl.ejdev.dixittogether.features.players.domain.entities.Player

internal data class Game(
    val players: List<Player>,
//    val results: List<PlayerResult>,
    val round: Int = 0,
)
