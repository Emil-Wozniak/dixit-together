package pl.ejdev.dixittogether.features.game.view.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import pl.ejdev.dixittogether.features.core.shared.GAME_COLORS
import pl.ejdev.dixittogether.features.core.shared.Title
import pl.ejdev.dixittogether.features.core.view.pages.View
import pl.ejdev.dixittogether.features.core.view.widgets.DixitButton
import pl.ejdev.dixittogether.features.game.domain.entities.PlayerResult
import pl.ejdev.dixittogether.features.game.domain.entities.incrementScore
import pl.ejdev.dixittogether.features.game.view.components.PlayersCards
import pl.ejdev.dixittogether.features.game.view.components.PlayersDetails
import pl.ejdev.dixittogether.features.game.view.components.Votes
import pl.ejdev.dixittogether.features.game.view.model.GameViewModel
import pl.ejdev.dixittogether.features.game.view.model.RoundViewModel
import pl.ejdev.dixittogether.features.players.domain.entities.Player
import pl.ejdev.dixittogether.features.players.view.model.PlayerViewModel

@Composable
internal fun GameScreen(
    navController: NavHostController,
    playerViewModel: PlayerViewModel = viewModel(),
    gameViewModel: GameViewModel = viewModel(),
    roundViewModel: RoundViewModel = viewModel()
) {
    val players: List<Player> = playerViewModel.getPlayers()
    gameViewModel.start(players)
    roundViewModel.start(players, playerViewModel.getNarrator())

    fun vote(currentVoter: Color, cardColor: Color) {
        roundViewModel.vote(currentVoter, cardColor, players)
    }

    fun finishRound() {
        val newPlayers = playerViewModel.getPlayers()
        roundViewModel.finishRound(
            newPlayers = newPlayers,
            narrator = playerViewModel.getNarrator()?.getColorOrDefault()
        ) {
            gameViewModel
                .getPlayersResults()
                .map(PlayerResult::incrementScore)
                .let(gameViewModel::finishRound)
            playerViewModel.nextNarrator()
        }
    }

    View(navController = navController) {
        Column {
            Narrator(roundViewModel.narrator.value)
            Title(
                text = "Round: ${gameViewModel.getRound()}",
                size = 6.em,
                lineHeight = 1.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
            )
            Votes(roundViewModel.votes)

            PlayersCards(
                gameViewModel,
                roundViewModel.voters.value.firstOrNull(),
                roundViewModel.cardsVotes,
                ::vote
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
                    .padding(4.dp)
            ) {
                DixitButton(
                    text = "End round",
                    active = roundViewModel.voters.value.isEmpty(),
                    onClick = ::finishRound,
                    width = 210,
                    height = 45
                )
            }
            PlayersDetails(gameViewModel)
        }
    }
}

@Composable
private fun Narrator(currentVoter: Color?) {
    Row(modifier = Modifier.height(18.dp), horizontalArrangement = Arrangement.Absolute.Right) {
        Title(
            text = "Narrator: ",
            size = 4.em,
            lineHeight = 1.sp,
            modifier = Modifier.offset(x = 1.dp, y = 1.dp)
        )
        currentVoter?.let {
            Card(
                modifier = Modifier
                    .padding(top = 6.dp)
                    .width(12.dp)
                    .height(12.dp)
                    .semantics { testTag = "current-voter" },
                colors = CardDefaults.cardColors(
                    containerColor = it,
                    contentColor = MaterialTheme.colorScheme.surface
                ),
            ) { }
        }
    }
}

@Preview(name = "GameScreen", backgroundColor = 0xFFFFFFFF)
@Composable
fun GameScreenPreview() {
    val players = GAME_COLORS.mapIndexed { index, gameColor -> Player("player $index", gameColor) }
    val playerViewModel: PlayerViewModel = PlayerViewModel().apply {
        players.forEach(::add)
    }
    val gameViewModel = GameViewModel()
    val roundViewModel = RoundViewModel()
    val navController = rememberNavController()
    GameScreen(
        navController,
        playerViewModel, gameViewModel, roundViewModel
    )
}
