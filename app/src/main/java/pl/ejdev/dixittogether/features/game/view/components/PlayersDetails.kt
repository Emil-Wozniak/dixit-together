package pl.ejdev.dixittogether.features.game.view.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pl.ejdev.dixittogether.features.core.shared.GAME_COLORS
import pl.ejdev.dixittogether.features.game.view.model.GameViewModel
import pl.ejdev.dixittogether.features.players.domain.entities.Player

@Composable
fun PlayersDetails(gameViewModel: GameViewModel) {
    gameViewModel.getPlayerDetailsPairs()
        .map { (a, b) ->
            Row {
                Column {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(fraction = 0.5f)
                            .padding(4.dp)
                    ) {
                        Icon(
                            Icons.Outlined.AccountCircle,
                            contentDescription = "player icon",
                            tint = a.color
                        )
                        Text(text = "Player ${a.name}")
                        Text(text = " ${a.score}")
                    }
                }
                Column {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(fraction = 0.5f)
                            .padding(4.dp)
                    ) {
                        Icon(
                            Icons.Outlined.AccountCircle,
                            contentDescription = "player icon",
                            tint = b.color
                        )
                        Text(text = b.name)
                        Text(text = " ${b.score}")
                    }
                }
            }
        }
}

@Preview(name = "PlayersDetails", backgroundColor = 0xFFFFFFFF)
@Composable
fun PlayersDetails() {
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
    PlayersDetails(gameViewModel)

}