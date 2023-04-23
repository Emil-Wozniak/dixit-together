package pl.ejdev.dixittogether.features.game.view.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import pl.ejdev.dixittogether.features.core.shared.GAME_COLORS
import pl.ejdev.dixittogether.features.core.shared.Title
import pl.ejdev.dixittogether.features.core.view.pages.View
import pl.ejdev.dixittogether.features.game.domain.entities.PlayerResult
import pl.ejdev.dixittogether.features.game.domain.entities.incrementScore
import pl.ejdev.dixittogether.features.game.view.components.PlayersCards
import pl.ejdev.dixittogether.features.game.view.components.PlayersDetails
import pl.ejdev.dixittogether.features.game.view.components.Votes
import pl.ejdev.dixittogether.features.game.view.model.GameViewModel
import pl.ejdev.dixittogether.features.players.domain.entities.Player
import pl.ejdev.dixittogether.features.players.view.model.PlayerViewModel

@Composable
internal fun GameScreen(
    navController: NavHostController,
    playerViewModel: PlayerViewModel = viewModel(),
    gameViewModel: GameViewModel = viewModel()
) {
    val players = playerViewModel.getAll()
    val playersToVote: List<Color> = players.map(Player::getColorOrDefault)
    gameViewModel.start(players)

    var votes by remember { mutableStateOf(playersToVote) }
    var voters by remember { mutableStateOf(playersToVote) }
    var currentVoter by remember { mutableStateOf<Color?>(voters[0]) }

    fun vote(color: Color) {
        votes = votes.filter { it != color }
        voters = voters.filter { it != color }
        if (voters.size > 0) {
            currentVoter = voters[0]
        } else {
            currentVoter = null
        }
    }

    View(navController = navController) {
        Column {
            Row {
                Title(
                    text = "Round: ${gameViewModel.getRound()}",
                    size = 8.em
                )
            }
            Votes(votes)
            Row {
                Column {
                    currentVoter?.let {
                        Card(
                            modifier = Modifier
                                .width(20.dp)
                                .height(20.dp)
                                .semantics { testTag = "current-voter" },
                            colors = CardDefaults.cardColors(
                                containerColor = it,
                                contentColor = MaterialTheme.colorScheme.surface
                            ),
                        ) { }
                    }
                }

            }
            PlayersCards(gameViewModel, currentVoter, ::vote)
            PlayersDetails(gameViewModel)
            Row {
                Button(
                    enabled = voters.isEmpty(),
                    onClick = {
                        gameViewModel
                            .getPlayersResults()
                            .map(PlayerResult::incrementScore)
                            .let(gameViewModel::finishRound)
                        votes = playersToVote
                        voters = playersToVote
                        currentVoter = voters[0]
                    }) {
                    Text(text = "End round")
                }
            }
        }
    }
}

@Preview(name = "GameScreen", backgroundColor = 0xFFFFFFFF)
@Composable
fun GameScreenPreview() {
    val navController = rememberNavController()
    GameScreen(navController, PlayerViewModel().apply {
        add(Player("Emil", GAME_COLORS[0]))
        add(Player("Ola", GAME_COLORS[1]))
        add(Player("Bartosz", GAME_COLORS[2]))
        add(Player("Michał", GAME_COLORS[3]))
        add(Player("Gosia", GAME_COLORS[4]))
        add(Player("Ewa", GAME_COLORS[5]))
    })
}
