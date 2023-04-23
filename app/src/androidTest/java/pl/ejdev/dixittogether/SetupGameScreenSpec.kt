package pl.ejdev.dixittogether

import androidx.compose.ui.test.*
import org.junit.Test
import pl.ejdev.dixittogether.features.players.view.screens.PlayersScreen

private const val ADD_USER_BTN = "add-user-btn"
private const val USER_NAME_TEXT_FIELD = "user-name-text-field"
private const val ADD_USER_ICON = "add-user-icon"

@OptIn(ExperimentalTestApi::class)
class SetupGameScreenSpec : InstrumentalTest {
    @Test
    fun `Setup game screen has add user button`() {
        runAndroidComposeUiTest {
            content { PlayersScreen(it) }
            tag(ADD_USER_BTN) {
                isDisplayed()
                isDisabled()
            }
        }
    }

    @Test
    fun `Setup game screen has add user name text field`() {
        runAndroidComposeUiTest {
            content { PlayersScreen(it) }
            tag(USER_NAME_TEXT_FIELD) {
                isDisplayed()
                isEnabled()
                click().write("Emil")
            }
            tag(ADD_USER_BTN) {
                isEnabled()
                click()
            }
        }
    }

    @Test
    fun `Setup game screen has add user icon`() {
        runAndroidComposeUiTest {
            content { PlayersScreen(it) }
            tag(ADD_USER_ICON) { isDisplayed() }
        }
    }
}