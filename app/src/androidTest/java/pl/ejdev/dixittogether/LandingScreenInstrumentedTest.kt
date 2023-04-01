package pl.ejdev.dixittogether

import androidx.compose.ui.test.*
import org.junit.jupiter.api.Test
import pl.ejdev.dixittogether.features.core.view.pages.LandingScreen

private const val HOME_BTN = "home-btn"
private const val PROFILE_BTN = "profile-btn"

@OptIn(ExperimentalTestApi::class)
internal class LandingScreenInstrumentedTest : InstrumentalTest {

    @Test
    fun `Landing screen has profile button`() {
        runAndroidComposeUiTest {
            content { LandingScreen(it) }
            tag(HOME_BTN) { isDisplayed() }
        }
    }

    @Test
    fun `Landing screen has home button`() {
        runAndroidComposeUiTest {
            content { LandingScreen(it) }
            tag(PROFILE_BTN) { isDisplayed() }
        }
    }
}
