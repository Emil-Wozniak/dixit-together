package pl.ejdev.dixittogether.features.players.view.model

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import pl.ejdev.dixittogether.features.players.domain.entities.Player

class PlayerViewModel : ViewModel() {

    private val state = mutableStateOf(listOf<Player>())

    fun add(player: Player) {
        state.value = state.value.toMutableList().apply { add(player) }
    }

    fun getAll(): List<Player> =
        state.value.also { Log.i(this::class.java.simpleName, it.toString()) }
}