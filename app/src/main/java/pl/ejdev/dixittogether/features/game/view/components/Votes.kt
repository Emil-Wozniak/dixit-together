package pl.ejdev.dixittogether.features.game.view.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pl.ejdev.dixittogether.features.core.shared.GAME_COLORS
import pl.ejdev.dixittogether.features.core.shared.ShadedComponent
import pl.ejdev.dixittogether.features.core.shared.shadingColor

@Composable
internal fun Votes(votes: List<Color>) {
    votes.map { color ->
        val linearGradient: Brush = Brush.linearGradient(
            0.2f to color.copy(alpha = 0.8f),
            0.3f to shadingColor,
            0.6f to color,
        )
        ShadedComponent(
            modifier = Modifier
                .width(20.dp)
                .height(20.dp)
                .padding(1.dp),
            roundCornerPercentage = 50,
            shadow = 0,
            linearGradient = linearGradient
        ) {
            Surface(
                color = color,
                contentColor = color,
                shape = CircleShape
            ) {}
        }
    }

}

@Composable
@Preview(name = "Votes component", showBackground = true)
internal fun VotesPreview() {
    val state = remember {
        mutableStateOf(GAME_COLORS.map { it.color })
    }
    Votes(votes = state.value)
}
