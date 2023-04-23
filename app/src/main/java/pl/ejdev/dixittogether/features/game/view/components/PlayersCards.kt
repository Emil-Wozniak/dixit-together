package pl.ejdev.dixittogether.features.game.view.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pl.ejdev.dixittogether.features.core.shared.GAME_COLORS
import pl.ejdev.dixittogether.features.game.view.model.GameViewModel
import pl.ejdev.dixittogether.features.players.domain.entities.Player

@Composable
internal fun PlayersCards(
    gameViewModel: GameViewModel,
    currentVoter: Color?,
    vote: (color: Color) -> Unit
) {
    gameViewModel.getResultColorPairs()
        .map { (first, second) ->
            Row {
                Column { GameCard(first, fraction = 0.5f, currentVoter, vote) }
                Column { GameCard(second, fraction = 1f, currentVoter, vote) }
            }
        }
}

@Composable
private fun GameCard(
    color: Color,
    fraction: Float,
    currentVoter: Color?,
    vote: (color: Color) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(fraction = fraction)
            .height(120.dp)
            .padding(4.dp)
            .clickable(onClick = {
                currentVoter?.let(vote)
            }),
        colors = CardDefaults.cardColors(
            containerColor = color,
            contentColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
    }
}

@Preview(name = "PlayersCards", backgroundColor = 0xFFFFFFFF)
@Composable
internal fun PlayersCardsPreview() {
    val gameViewModel = GameViewModel().apply {
        listOf(
            Player("Emil", GAME_COLORS[0]),
            Player("Ola", GAME_COLORS[1]),
            Player("Bartosz", GAME_COLORS[2]),
            Player("Michał", GAME_COLORS[3]),
            Player("Gosia", GAME_COLORS[4]),
            Player("Ewa", GAME_COLORS[5])
        ).let(::start)
    }
    PlayersCards(gameViewModel, GAME_COLORS[0].color) {}
}
