package pl.ejdev.dixittogether

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.test.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalTestApi::class)
internal inline infix fun AndroidComposeUiTest<ComponentActivity>.content(
    crossinline composable: @Composable (nav: NavHostController) -> Unit
) = this.setContent {
    val navController: NavHostController = rememberNavController()
    composable(navController)
}
