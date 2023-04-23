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
    gameViewModel
        .getPlayerDetailsPairs()
        .map {
            Row {
                val firstPlayer = it[0]
                if (it.size > 1) {
                    val secondPlayer = it[1]
                    Column {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(fraction = 0.5f)
                                .padding(4.dp)
                        ) {
                            Icon(
                                Icons.Outlined.AccountCircle,
                                contentDescription = "player icon",
                                tint = firstPlayer.color
                            )
                            Text(text = "Player ${firstPlayer.name}")
                            Text(text = " ${firstPlayer.score}")
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
                                tint = secondPlayer.color
                            )
                            Text(text = secondPlayer.name)
                            Text(text = " ${secondPlayer.score}")
                        }
                    }
                } else {
                    Column {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(fraction = 0.5f)
                                .padding(4.dp)
                        ) {
                            Icon(
                                Icons.Outlined.AccountCircle,
                                contentDescription = "player icon",
                                tint = firstPlayer.color
                            )
                            Text(text = "Player ${firstPlayer.name}")
                            Text(text = " ${firstPlayer.score}")
                        }
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