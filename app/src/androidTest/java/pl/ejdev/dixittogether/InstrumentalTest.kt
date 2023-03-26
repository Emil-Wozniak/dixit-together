package pl.ejdev.dixittogether

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.test.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal interface InstrumentalTest {

    @OptIn(ExperimentalTestApi::class)
    fun AndroidComposeUiTest<ComponentActivity>.tag(
        name: String,
        interaction: SemanticsNodeInteractionDsl.() -> Unit
    ) = onNode(hasTestTag(name), useUnmergedTree = true).run {
        interaction(SemanticsNodeInteractionDsl(this))
    }

    class SemanticsNodeInteractionDsl(private val interaction: SemanticsNodeInteraction) {
        internal fun isDisplayed() {
            interaction.assertIsDisplayed()
        }

        internal fun isNotDisplayed() {
            interaction.assertIsNotDisplayed()
        }

        internal fun isEnabled() {
            interaction.assertIsEnabled()
        }

        internal fun isDisabled() {
            interaction.assertIsNotEnabled()
        }

        internal fun click() = apply {
            interaction.performClick()
        }

        internal fun write(text: String) {
            interaction.performTextInput(text)
        }
    }
}

@OptIn(ExperimentalTestApi::class)
internal inline infix fun AndroidComposeUiTest<ComponentActivity>.content(
    crossinline composable: @Composable (nav: NavHostController) -> Unit
) = this.setContent {
    val navController: NavHostController = rememberNavController()
    composable(navController)
}
