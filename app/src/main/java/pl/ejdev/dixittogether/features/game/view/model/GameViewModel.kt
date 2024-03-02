package pl.ejdev.dixittogether.features.game.view.model

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import pl.ejdev.dixittogether.features.game.domain.entities.Game
import pl.ejdev.dixittogether.features.game.domain.entities.PlayerResult
import pl.ejdev.dixittogether.features.game.domain.entities.toPlayerDetails
import pl.ejdev.dixittogether.features.game.view.service.roundService
import pl.ejdev.dixittogether.features.players.domain.entities.Player

internal class GameViewModel : ViewModel() {
    private var results = mutableStateListOf<PlayerResult>()
    private var players = mutableStateListOf<Player>()
    private var round = mutableIntStateOf(0)

    fun start(players: List<Player>) {
        if (this.players.isEmpty()) {
            this.players += players
        }
        if (results.isEmpty()) {
            results += players.map(::PlayerResult)
        }
    }

    fun getPlayersResults(): List<PlayerResult> = results.toList()

    fun getRound(): Int = round.intValue

    fun finishRound(cardsVotes: Map<Color, MutableList<Color>>) {
        val actualResults = roundService(results, cardsVotes)
        this.round.intValue += 1
        val oldValues = this.results.toList()
        this.results.clear()
        this.results += oldValues
            .map { playerResult ->
                actualResults
                    .find { it.player.gameColor!!.color == playerResult.player.gameColor!!.color }
                    ?.let {
                        playerResult.score = playerResult.score + it.score
                    }
                playerResult
            }
    }

    fun getPlayerDetails() = results.map(PlayerResult::toPlayerDetails)
}

private operator fun PlayerResult.plus(results: List<PlayerResult>) = this.apply {
    val score = results.find { it.player.name == player.name }
        ?.score
        ?: 0
    this.score = score
}
