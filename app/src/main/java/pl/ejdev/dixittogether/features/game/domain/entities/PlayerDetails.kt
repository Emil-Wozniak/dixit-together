package pl.ejdev.dixittogether.features.game.domain.entities

import androidx.compose.ui.graphics.Color

data class PlayerDetails(
    val name: String,
    val color: Color,
    val score: Int
)