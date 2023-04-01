package pl.ejdev.dixittogether.features.core.view.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay
import pl.ejdev.dixittogether.features.core.shared.SUB_TITLE
import pl.ejdev.dixittogether.features.core.shared.TITLE
import pl.ejdev.dixittogether.features.core.shared.Title
import pl.ejdev.dixittogether.features.core.view.widgets.GameButton

private const val START_BUTTON_LABEL = "Start"
private const val SPLASH_WAIT_TIME: Long = 2000

@Composable
internal fun LandingScreen(navController: NavHostController) {
    LaunchedEffect(Unit) {
        delay(SPLASH_WAIT_TIME)
    }
    View(navController) {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 36.dp)
        ) {
            Title(TITLE, size = 24.em, lineHeight = 1.5.em)
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 36.dp)
        ) {
            Title(SUB_TITLE, size = 14.em, lineHeight = 1.em)
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 36.dp)
        ) {
            GameButton(START_BUTTON_LABEL) {
                navController.navigate("setup")
            }
        }
    }
}