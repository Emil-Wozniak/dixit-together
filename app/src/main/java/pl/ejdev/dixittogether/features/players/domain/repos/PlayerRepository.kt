package pl.ejdev.dixittogether.features.players.domain.repos

import pl.ejdev.dixittogether.features.players.domain.entities.Player


interface PlayerRepository {
    fun addPlayer(player: Player)
}