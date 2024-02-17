package pl.ejdev.dixittogether.features.players.view.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import pl.ejdev.dixittogether.features.core.shared.Title
import pl.ejdev.dixittogether.features.core.view.pages.View
import pl.ejdev.dixittogether.features.core.view.widgets.GameButton
import pl.ejdev.dixittogether.features.players.domain.entities.Player
import pl.ejdev.dixittogether.features.players.view.components.AddPlayer
import pl.ejdev.dixittogether.features.players.view.components.ShowPlayers
import pl.ejdev.dixittogether.features.players.view.model.PlayerViewModel

private const val SCREEN_TITLE = "Players:"
private const val NEXT_BUTTON_LABEL = "Next"
private const val NEXT_BUTTON_DESTINATION = "game"

@Composable
internal fun PlayersScreen(
    navController: NavHostController,
    playersViewModel: PlayerViewModel = viewModel()
) {
    View(navController = navController) {
        Title(
            text = SCREEN_TITLE,
            size = 6.em,
            lineHeight = 1.em,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 8.dp)
        )
        AddPlayer(
            usedColors = playersViewModel.getAll().mapNotNull(Player::gameColor),
            onAddUser = playersViewModel::add
        )
        ShowPlayers(playersViewModel.getAll())
        GameButton(
            text = NEXT_BUTTON_LABEL,
            active = playersViewModel.getAll().size > 1,
            onClick =  {
                navController.navigate(NEXT_BUTTON_DESTINATION)
            }
        )
    }
}

@Preview
@Composable
fun PlayersScreenPreview() {
    val navController = rememberNavController()
    PlayersScreen(navController)
}
