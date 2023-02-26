@file:OptIn(ExperimentalTextApi::class)

package pl.ejdev.dixittogether

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.em
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import pl.ejdev.dixittogether.layout.view.SetupGameScreen
import pl.ejdev.dixittogether.shared.TITLE
import pl.ejdev.dixittogether.shared.Title
import pl.ejdev.dixittogether.ui.theme.DixitTogetherTheme
import pl.ejdev.dixittogether.layout.view.ProfileScreen
import pl.ejdev.dixittogether.layout.view.LandingScreen

internal val startScreenBackground = Color(253, 147, 0)

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DixitTogetherTheme {
                val navController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    NavHost(navController = navController, startDestination = "home") {
                        composable("home") { LandingScreen(navController) }
                        composable("profile") { ProfileScreen(navController) }
                        composable("setup") { SetupGameScreen(navController) }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DixitTogetherTheme {
        Title(TITLE, 24.em)
    }
}