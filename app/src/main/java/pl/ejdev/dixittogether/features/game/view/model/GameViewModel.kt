package pl.ejdev.dixittogether.features.game.view.model

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import pl.ejdev.dixittogether.features.game.domain.entities.Game
import pl.ejdev.dixittogether.features.game.domain.entities.PlayerDetails
import pl.ejdev.dixittogether.features.game.domain.entities.PlayerResult
import pl.ejdev.dixittogether.features.game.domain.entities.toPlayerDetails
import pl.ejdev.dixittogether.features.players.domain.entities.Player

class GameViewModel : ViewModel() {
    private val state = mutableStateOf(
        Game(
            players = listOf(),
            results = listOf(),
            round = 0
        )
    )

    fun start(players: List<Player>) {
        state.value = Game(
            players = players,
            results = players.map { PlayerResult(it, 0) },
        )
    }

    fun getPlayersResults(): List<PlayerResult> = state.value.results

    fun getRound(): Int = state.value.round

    fun finishRound(results: List<PlayerResult>) {
        state.value = Game(
            players = state.value.players,
            results = state.value.results.map { it addScore results },
            round = state.value.round + 1
        )
    }

    fun getResultColorPairs(): List<Pair<Color, Color>> =
        state.value
            .results
            .asSequence()
            .zipWithNext()
            .withIndex()
            .filter { it.index % 2 == 0 }
            .map(IndexedValue<Pair<PlayerResult, PlayerResult>>::value)
            .map { (a, b) -> a.colorOrDefault() to b.colorOrDefault() }
            .toList()

 fun getPlayerDetailsPairs(): List<Pair<PlayerDetails, PlayerDetails>> =
        state.value
            .results
            .asSequence()
            .zipWithNext()
            .withIndex()
            .filter { it.index % 2 == 0 }
            .map(IndexedValue<Pair<PlayerResult, PlayerResult>>::value)
            .map { (a, b) -> a.toPlayerDetails() to b.toPlayerDetails() }
            .toList()
}

private fun PlayerResult.colorOrDefault(): Color = this.player.gameColor?.color ?: Color.Black

private infix fun PlayerResult.addScore(results: List<PlayerResult>) = this.apply {
    val score = results.find { it.player.name == player.name }?.score ?: 0
    this.score = score
}
