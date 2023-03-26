package pl.ejdev.dixittogether.features.players.domain.repos

import pl.ejdev.dixittogether.entity.Player

interface PlayerRepository {
    fun addPlayer(player: Player)
}