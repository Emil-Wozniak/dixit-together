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

@RunWith(AndroidJUnit4::class)
internal interface InstrumentalTest {

    infix fun<T: Any> Any.shouldBe(t: T) = assertEquals(this, t)

    @OptIn(ExperimentalTestApi::class)
    fun AndroidComposeUiTest<ComponentActivity>.tag(
        name: String,
        interaction: SemanticsNodeInteractionDsl.() -> Unit
    ) = onNode(hasTestTag(name), useUnmergedTree = true).run {
        interaction(SemanticsNodeInteractionDsl(this))
    }
    @OptIn(ExperimentalTestApi::class)
    fun AndroidComposeUiTest<ComponentActivity>.tags(
        name: String,
        interaction: List<SemanticsNode>.() -> Unit
    ) = onAllNodes(hasTestTag(name), useUnmergedTree = true).run {
        interaction(this.fetchSemanticsNodes())
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
