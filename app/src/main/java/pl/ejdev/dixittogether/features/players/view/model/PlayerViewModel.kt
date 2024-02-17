package pl.ejdev.dixittogether.features.players.view.model

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import pl.ejdev.dixittogether.features.core.shared.Logger
import pl.ejdev.dixittogether.features.core.shared.LogWrapper.get
import pl.ejdev.dixittogether.features.core.shared.LogWrapper.logger
import pl.ejdev.dixittogether.features.players.domain.entities.Player

internal class PlayerViewModel : ViewModel() {
    private val log: Logger = this get logger

    private var state = mutableStateOf(listOf<Player>())
    private var narratorIndex = mutableIntStateOf(0)
    private var players = mutableStateOf(listOf<Player>())
    private var narrator = mutableStateOf<Player?>(null)


    fun add(player: Player) {
        state.value = state.value.toMutableList().apply {
            add(player)
        }
    }

    fun getAll(): List<Player> =
        state.value
            .also { allPlayers ->
                val narratorIndex = narratorIndex.intValue
                if (state.value.size > narratorIndex && !state.value[narratorIndex].narrator) {
                    changeNarrator(narratorIndex)
                }
                if (allPlayers.all { !it.narrator }) {
                    changeNarrator(narratorIndex)
                }
                players.value = allPlayers.filter { !it.narrator }
                narrator.value = allPlayers.find { it.narrator }
            }
            .also(log::info)

    fun getPlayers() = players.value
    fun getNarrator() = narrator.value
    fun nextNarrator() {
        changeNarrator(narratorIndex.intValue++)
        getAll()
    }

    private fun changeNarrator(narratorIndex: Int) {
        state.value = state.value.toMutableList().mapIndexed { index, player ->
            if (index == narratorIndex) player.apply { narrator = true }
            else player.apply { narrator = false }
        }
    }

    fun clear() {
        state = mutableStateOf(listOf())
    }
}