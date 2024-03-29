package pl.ejdev.dixittogether.features.core.shared

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb

private val red = Color(217, 34, 20)
private val pink = Color(211, 63, 139)
private val blue = Color(71, 114, 183)
private val white = Color(252, 253, 255)
private val green = Color(106, 142, 128)
private val yellow = Color(253, 240, 50)

internal fun Color.hex(): Hex =
    String.format("#%06X", 0xFFFFFF and this.toArgb())

internal fun Color.toGameColor(): GameColor =
    this.hex() of this

internal typealias Hex = String

internal infix fun Hex.of(color: Color) = GameColor(this, color)
internal fun String.toColor() = Color(android.graphics.Color.parseColor(this))

data class GameColor(val hex: Hex, val color: Color)

val GAME_COLORS: List<GameColor> =
    listOf(
        green.toGameColor(),
        yellow.toGameColor(),
        red.toGameColor(),
        pink.toGameColor(),
        blue.toGameColor(),
        white.toGameColor(),
    )