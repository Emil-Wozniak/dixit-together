package pl.ejdev.dixittogether.features.game.view.model

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import pl.ejdev.dixittogether.features.players.domain.entities.Player

internal class RoundViewModel : ViewModel() {
    private val votes = mutableStateOf<List<Color>>(mutableListOf())
    private val voters = mutableStateOf<List<Color>>(mutableListOf())
    private var narrator = mutableStateOf<Color?>(null)
    private val selectedVotes = mutableStateListOf<Color>()
    private val cardsVotes = mutableStateMapOf<Color, MutableList<Color>>()

    fun start(players: List<Player>, narrator: Player?) {
        val playersColors: List<Color> = players.map(Player::getColorOrDefault)
        votes.value = playersColors
        voters.value = playersColors
        this.narrator.value = narrator?.getColorOrDefault()
        cardsVotes.putAll(playersColors.map { it to mutableListOf<Color>() }.toTypedArray())
    }

    fun vote(currentVoter: Color, cardColor: Color, players: List<Player>) {
        val playersColors: List<Color> = players.map(Player::getColorOrDefault)
        this.selectedVotes.add(currentVoter)
        val colors = playersColors.filter {
            it !in this.selectedVotes.toList()
        }.filter { it != currentVoter }
        this.votes.value = colors
        this.voters.value = colors
        val actualMap = this.cardsVotes[cardColor]
        val newColors = actualMap?.also { it.add(currentVoter) } ?: mutableListOf(currentVoter)
        this.cardsVotes[cardColor] = newColors
    }

    fun roundVotes() = cardsVotes.toMap()

    fun narrator() = narrator.value

    fun votes() = votes.value
    fun voters() = voters.value
    fun cardsVotes() = cardsVotes

    fun finishRound(
        newPlayers: List<Player>,
        narrator: Color?,
    ) {
        val playersColors = newPlayers.map { it.getColorOrDefault() }
        this.votes.value = playersColors
        this.voters.value = playersColors
        this.narrator.value = narrator
        this.selectedVotes.clear()
        this.cardsVotes.clear()
        this.cardsVotes.putAll(playersColors.map { it to mutableListOf<Color>() }
            .toTypedArray())
    }
}