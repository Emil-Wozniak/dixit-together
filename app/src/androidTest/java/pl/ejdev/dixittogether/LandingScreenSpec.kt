package pl.ejdev.dixittogether

import androidx.compose.ui.test.*
import br.com.colman.kotest.FreeSpec
import pl.ejdev.dixittogether.features.core.view.pages.LandingScreen

@OptIn(ExperimentalTestApi::class)
internal class LandingScreenSpec : FreeSpec({
    "Landing screen has profile button" - {
        runAndroidComposeUiTest {
            content { LandingScreen(it) }
            tag("home-btn") { isDisplayed() }
        }
    }
    "Landing screen has home button" - {
        runAndroidComposeUiTest {
            content { LandingScreen(it) }
            tag("profile-btn") { isDisplayed() }
        }
    }
})