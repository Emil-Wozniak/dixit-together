package pl.ejdev.dixittogether

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.semantics.SemanticsNode
import androidx.compose.ui.test.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.TestCase.assertEquals
import org.junit.runner.RunWith

@OptIn(ExperimentalTestApi::class)
internal inline infix fun AndroidComposeUiTest<ComponentActivity>.content(
    crossinline composable: @Composable (nav: NavHostController) -> Unit
) = this.setContent {
    val navController: NavHostController = rememberNavController()
    composable(navController)
}
