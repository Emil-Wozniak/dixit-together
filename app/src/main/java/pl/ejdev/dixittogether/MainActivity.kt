package pl.ejdev.dixittogether

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.em
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import pl.ejdev.dixittogether.features.core.data.sources.appModule
import pl.ejdev.dixittogether.features.core.shared.Screen
import pl.ejdev.dixittogether.features.core.shared.TITLE
import pl.ejdev.dixittogether.features.core.shared.Title
import pl.ejdev.dixittogether.features.core.view.pages.LandingScreen
import pl.ejdev.dixittogether.features.core.view.pages.ProfileScreen
import pl.ejdev.dixittogether.features.game.view.screens.GameScreen
import pl.ejdev.dixittogether.features.players.view.screens.SetupScreen
import pl.ejdev.dixittogether.ui.theme.DixitTogetherTheme

internal val startScreenBackground = Color(red = 253, green = 147, blue = 0)

private const val NO_CURRENT_VIEW_MODEL_STORE =
    "No ViewModelStoreOwner was provided via LocalViewModelStoreOwner"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startKoin {
            androidLogger()
            androidContext(this@MainActivity)
            modules(appModule)
        }
        setContent {
            DixitTogetherTheme {
                val viewModelStoreOwner = LocalViewModelStoreOwner.currentView()
                val navController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    NavHost(navController = navController, startDestination = "home") {
                        composable(Screen.HOME) {
                            LandingScreen(navController)
                        }
                        composable(Screen.PROFILE) {
                            ProfileScreen(navController)
                        }
                        composable(Screen.SETUP) {
                            SetupScreen(navController, viewModel(viewModelStoreOwner))
                        }
                        composable(Screen.GAME) {
                            CompositionLocalProvider(LocalViewModelStoreOwner provides viewModelStoreOwner) {
                                GameScreen(navController)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun LocalViewModelStoreOwner?.currentView(): ViewModelStoreOwner =
    checkNotNull(this?.current) { NO_CURRENT_VIEW_MODEL_STORE }

internal fun NavGraphBuilder.composable(
    route: Screen,
    arguments: List<NamedNavArgument> = emptyList(),
    deepLinks: List<NavDeepLink> = emptyList(),
    content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit
) {
     return composable(route.path, arguments, deepLinks, content = content)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DixitTogetherTheme {
        Title(TITLE, 24.em)
    }
}