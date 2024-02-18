package pl.ejdev.dixittogether.features.core.view.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay
import pl.ejdev.dixittogether.features.core.shared.Screen
import pl.ejdev.dixittogether.features.core.view.widgets.DixitButton

private const val START_BUTTON_LABEL = "Start"
private const val SPLASH_WAIT_TIME: Long = 2000

@Composable
internal fun LandingScreen(navController: NavHostController) {
    LaunchedEffect(Unit) { delay(SPLASH_WAIT_TIME) }
    View(navController) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 36.dp)
        ) {
            DixitButton(START_BUTTON_LABEL) {
                navController.navigate(Screen.SETUP.path)
            }
            DixitButton("Recent games") {
                navController.navigate(Screen.SETUP.path)
            }
            DixitButton("Players") {
                navController.navigate(Screen.SETUP.path)
            }
            DixitButton("Settings") {
                navController.navigate(Screen.SETUP.path)
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun LandingScreenPreview() {
    val navController = rememberNavController()
    LandingScreen(navController)
}