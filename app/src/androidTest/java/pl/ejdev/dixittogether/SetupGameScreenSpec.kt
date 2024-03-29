package pl.ejdev.dixittogether

import androidx.compose.ui.test.*
import br.com.colman.kotest.FreeSpec
import pl.ejdev.dixittogether.features.players.view.screens.SetupScreen

private const val ADD_USER_BTN = "add-user-btn"
private const val USER_NAME_TEXT_FIELD = "user-name-text-field"
private const val ADD_USER_ICON = "add-user-icon"

@OptIn(ExperimentalTestApi::class)
class SetupGameScreenSpec : FreeSpec({
    "Setup game screen has add user button" - {
        runAndroidComposeUiTest {
            content { SetupScreen(it) }
            tag(ADD_USER_BTN) {
                isDisplayed()
                isDisabled()
            }
        }
    }
    "Setup game screen has add user name text field" - {
        runAndroidComposeUiTest {
            content { SetupScreen(it) }
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
    "Setup game screen has add user icon" - {
        runAndroidComposeUiTest {
            content { SetupScreen(it) }
            tag(ADD_USER_ICON) { isDisplayed() }
        }
    }
})