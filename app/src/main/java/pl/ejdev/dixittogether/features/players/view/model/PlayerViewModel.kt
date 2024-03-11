package pl.ejdev.dixittogether.features.players.view.model

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import pl.ejdev.dixittogether.features.core.shared.Logger
import pl.ejdev.dixittogether.features.core.shared.LogWrapper.get
import pl.ejdev.dixittogether.features.core.shared.LogWrapper.logger
import pl.ejdev.dixittogether.features.players.domain.entities.Player

internal class PlayerViewModel : ViewModel() {
    private val log = this get logger

    private var state = mutableStateListOf<Player>()
    private var narratorIndex = mutableIntStateOf(0)
    private var players = mutableStateListOf<Player>()
    private var narrator = mutableStateOf<Player?>(null)

    fun add(player: Player) {
        if (!this.state.contains(player)) {
            this.state.add(player)
        }
    }

    fun addAll(players: List<Player>) = players.also(state::addAll)

    fun getAll(): List<Player> {
        val allPlayers = this.state.toList()
        val narratorIndex = narratorIndex.intValue
        if (this.state.size > 0) {
            when {
                this.state.size < narratorIndex -> error("A narrator cannot be of players amount $narratorIndex")
                this.state[narratorIndex].isNotNarrator() -> changeNarrator()
                allPlayers.all { !it.narrator } -> changeNarrator()
            }
            changePlayers()
        }

        return allPlayers
    }

    fun getPlayers() = players.toList()
    fun getNarrator() = narrator.value
    fun nextNarrator(): Player {
        changeNarratorIndex()
        changeNarrator()
        changePlayers()
        return this.narrator.value.let(::requireNotNull)
    }

    fun clear() = this.state.clear()

    private fun changePlayers() {
        this.players.clear()
        this.players += this.state.toList().filter { !it.narrator }
    }

    private fun changeNarrator() {
        val narratorIndex = this.narratorIndex.intValue
        this.narrator.value = this.state[narratorIndex].apply { narrator = true }
        val newState = this.state.toList().mapIndexed { index, player ->
            player.apply { narrator = index == narratorIndex }
        }
        this.state.clear()
        this.state += newState
    }

    private fun changeNarratorIndex() {
        val newIndex = narratorIndex.intValue + 1
        this.narratorIndex.intValue = if (newIndex >= state.size) 0 else newIndex
        log.info("Next narrator is ${state[narratorIndex.intValue].name}")
    }
}