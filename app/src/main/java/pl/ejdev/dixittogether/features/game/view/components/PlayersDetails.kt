package pl.ejdev.dixittogether.features.game.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pl.ejdev.dixittogether.features.core.shared.GAME_COLORS
import pl.ejdev.dixittogether.features.game.domain.entities.PlayerDetails
import pl.ejdev.dixittogether.features.game.view.model.GameViewModel
import pl.ejdev.dixittogether.features.players.domain.entities.Player

@Composable
fun PlayersDetails(gameViewModel: GameViewModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 50.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            gameViewModel.getPlayerDetailsPairs().map { players ->
                Row(modifier = Modifier.fillMaxWidth()) {
                    val firstPlayer = players[0]
                    if (players.size > 1) {
                        val secondPlayer = players[1]
                        Column(modifier = Modifier.fillMaxWidth(.5f)) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(fraction = 0.5f)
                                    .padding(4.dp)
                            ) {
                                PlayerIcon(firstPlayer)
                                Text(
                                    text = "${firstPlayer.score}",
                                    modifier = Modifier.padding(start = 4.dp)
                                )
                            }
                        }
                        Column(modifier = Modifier.fillMaxWidth()) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(fraction = 0.5f)
                                    .padding(4.dp)
                            ) {
                                PlayerIcon(player = secondPlayer)
                                Text(text = " ${secondPlayer.score}")
                            }
                        }
                    } else {
                        Column(modifier = Modifier.fillMaxWidth()) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(fraction = 0.5f)
                                    .padding(4.dp)
                            ) {
                                PlayerIcon(player = firstPlayer)
                                Text(text = " ${firstPlayer.score}")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun PlayerIcon(player: PlayerDetails) {
    Surface(
        modifier = Modifier.padding(end = 4.dp),
        shape = CircleShape,
    ) {
        Icon(
            Icons.Outlined.Person,
            contentDescription = "player icon",
            tint = player.color,
            modifier = Modifier.background(Color.LightGray)
        )
    }
}

@Preview(name = "PlayersDetails", showBackground = true)
@Composable
fun PlayersDetails() {
    val gameViewModel = GameViewModel().apply {
        GAME_COLORS.mapIndexed { index, gameColor ->
            Player("Gamer ${index}", gameColor)
        }.let { start(it) }
    }
    PlayersDetails(gameViewModel)
}