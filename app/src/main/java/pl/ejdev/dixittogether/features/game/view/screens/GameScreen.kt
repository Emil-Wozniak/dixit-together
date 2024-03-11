package pl.ejdev.dixittogether.features.game.view.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.HistoryEdu
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.font.FontWeight
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
    gameViewModel.start(players + listOfNotNull(playerViewModel.getNarrator()))
    roundViewModel.start(players, playerViewModel.getNarrator())

    fun vote(currentVoter: Color, cardColor: Color) =
        roundViewModel.vote(currentVoter, cardColor, players)

    fun finishRound() {
        gameViewModel.finishRound(roundViewModel.roundVotes())
        val narrator = playerViewModel.nextNarrator()
        val newPlayers = playerViewModel.getPlayers()
        roundViewModel.finishRound(
            newPlayers = newPlayers,
            narrator = narrator.getColorOrDefault()
        )
    }
    View(navController = navController) {
        Column {
            Title(
                text = "Round: ${gameViewModel.getRound()}",
                size = 6.em,
                lineHeight = 1.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
            )
            PlayersCards(
                roundViewModel.voters().firstOrNull(),
                roundViewModel.cardsVotes(),
                gameViewModel,
                vote = ::vote
            )
            RoundDetailsRows(roundViewModel)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
            ) {
                DixitButton(
                    text = "Next round",
                    active = roundViewModel.voters().isEmpty(),
                    onClick = ::finishRound,
                    width = 140,
                    height = 40,
                    size = 4.em
                )
            }
            PlayersDetails(gameViewModel)
        }
    }
}

@Composable
private fun RoundDetailsRows(roundViewModel: RoundViewModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 4.dp, end = 4.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth(0.2f)) {
            Title(
                text = "Narrator",
                size = 3.em,
                lineHeight = 1.sp,
                fontWeight = FontWeight.Black,
                modifier = Modifier
                    .fillMaxWidth(0.4f)
                    .offset(x = 1.dp, y = 1.dp)
            )
        }
        Column(modifier = Modifier.fillMaxWidth()) {
            Title(
                text = "Votes",
                size = 3.em,
                lineHeight = 1.sp,
                fontWeight = FontWeight.Black,
                modifier = Modifier
                    .offset(0.dp, 0.dp)
                    .semantics { testTag = "votes-title" }
            )
        }
    }
    Row(
        modifier = Modifier.height(36.dp),
        horizontalArrangement = Arrangement.Absolute.Right
    ) {
        Column(modifier = Modifier.fillMaxWidth(0.2f)) {
            Narrator(roundViewModel.narrator())
        }
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Votes(roundViewModel.votes())
            }
        }
    }
}

@Composable
private fun Narrator(currentVoter: Color?) {
    currentVoter?.let {
        Surface(
            shape = CircleShape,
            color = Color.Black
        ) {
            Icon(
                Icons.Rounded.HistoryEdu,
                contentDescription = "history icon",
                tint = it,
                modifier = Modifier.size(36.dp)
            )
        }
    }
}

@Composable
@Preview(name = "Game screen", showBackground = true)
fun GameScreenPreview() {
    val navController = rememberNavController()
    val players = GAME_COLORS.mapIndexed { index, gameColor -> Player("player $index", gameColor) }
    val playerViewModel = PlayerViewModel().apply { addAll(players) }
    val gameViewModel = GameViewModel()
    val roundViewModel = RoundViewModel()
    playerViewModel.getAll()
    GameScreen(
        navController,
        playerViewModel, gameViewModel, roundViewModel
    )
}
