package pl.ejdev.dixittogether.features.players.view.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import pl.ejdev.dixittogether.features.core.shared.Island_Moments
import pl.ejdev.dixittogether.features.core.shared.Title
import pl.ejdev.dixittogether.features.core.view.pages.View
import pl.ejdev.dixittogether.features.core.view.widgets.DixitButton
import pl.ejdev.dixittogether.features.core.view.widgets.DixitNumberButton
import pl.ejdev.dixittogether.features.players.domain.entities.Player
import pl.ejdev.dixittogether.features.players.view.components.AddPlayer
import pl.ejdev.dixittogether.features.players.view.components.ShowPlayers
import pl.ejdev.dixittogether.features.players.view.model.PlayerViewModel

private const val SCREEN_TITLE = "Setup game:"
private const val NEXT_BUTTON_LABEL = "Next"
private const val NEXT_BUTTON_DESTINATION = "game"

@Composable
internal fun SetupScreen(
    navController: NavHostController,
    playersViewModel: PlayerViewModel = viewModel()
) {
    val rounds = remember { mutableIntStateOf(10) }
    View(navController = navController) {
        Title(
            text = SCREEN_TITLE,
            size = 8.em,
            lineHeight = 1.em,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 8.dp)
        )
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .height(60.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Number of rounds",
                fontFamily = Island_Moments,
                fontSize = 6.em,
            )
            DixitNumberButton(rounds)
        }
        Text(
            text = "Players:",
            fontSize = 6.em,
            lineHeight = 1.em,
            fontFamily = Island_Moments,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
        )
        AddPlayer(
            usedColors = playersViewModel.getAll().mapNotNull(Player::gameColor),
            onAddUser = playersViewModel::add
        )
        ShowPlayers(playersViewModel.getAll())
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DixitButton(
                text = NEXT_BUTTON_LABEL,
                active = playersViewModel.getAll().size > 1,
                onClick = { navController.navigate(NEXT_BUTTON_DESTINATION) }
            )
        }
    }
}

@Preview
@Composable
fun PlayersScreenPreview() {
    val navController = rememberNavController()
    SetupScreen(navController)
}
