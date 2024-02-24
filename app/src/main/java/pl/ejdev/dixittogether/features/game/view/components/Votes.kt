package pl.ejdev.dixittogether.features.game.view.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import pl.ejdev.dixittogether.features.core.shared.GAME_COLORS
import pl.ejdev.dixittogether.features.core.shared.ShadedComponent
import pl.ejdev.dixittogether.features.core.shared.Title
import pl.ejdev.dixittogether.features.core.shared.componentColor
import pl.ejdev.dixittogether.features.core.shared.shadingColor

@Composable
internal fun Votes(votes: MutableState<List<Color>>) {
    Row(modifier = Modifier.padding(4.dp)) {
        Title(
            text = "Votes: ",
            size = 4.em,
            lineHeight = 1.sp,
            modifier = Modifier
                .offset(0.dp, 0.dp)
                .semantics { testTag = "votes-title" }
        )
        votes.value.map { color ->
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
}

@Composable
@Preview(name = "Votes component", showBackground = true)
internal fun VotesPreview() {
    val state = remember {
        mutableStateOf(GAME_COLORS.map { it.color })
    }
    Votes(votes = state)
}
