package pl.ejdev.dixittogether.features.game.view.model

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import pl.ejdev.dixittogether.features.game.domain.entities.Game
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
            results = players.map(::PlayerResult),
        )
    }

    fun getPlayersResults(): List<PlayerResult> = state.value.results

    fun getRound(): Int = state.value.round

    fun finishRound(results: List<PlayerResult>) {
        state.value = Game(
            players = state.value.players,
            results = state.value.results.map { playerResult -> playerResult + results },
            round = state.value.round + 1
        )
    }

    fun getResultColorPairs(): List<List<Color>> =
        state.value
            .results
            .chunked(2)
            .map { playerResults ->
                playerResults
                    .map(PlayerResult::player)
                    .map(Player::getColorOrDefault)
            }

    fun getPlayerDetailsPairs() =
        state.value
            .results
            .chunked(2)
            .map { it.map(PlayerResult::toPlayerDetails) }
}

private operator fun PlayerResult.plus(results: List<PlayerResult>) = this.apply {
    val score = results.find { it.player.name == player.name }
        ?.score
        ?: 0
    this.score = score
}
