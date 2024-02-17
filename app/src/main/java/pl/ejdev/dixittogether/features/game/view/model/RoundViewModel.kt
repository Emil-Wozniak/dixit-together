package pl.ejdev.dixittogether.features.game.view.model

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel

class RoundViewModel : ViewModel() {
    val votes = mutableStateOf<List<Color>>(mutableListOf())

    val voters = mutableStateOf<List<Color>>(mutableListOf())

    var narrator = mutableStateOf<Color?>(null)

    val selectedVotes = mutableStateListOf<Color>()
    val cardsVotes = mutableStateMapOf<Color, List<Color>>()

}