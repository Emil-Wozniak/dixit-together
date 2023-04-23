package pl.ejdev.dixittogether

import androidx.activity.ComponentActivity
import androidx.compose.ui.semantics.SemanticsNode
import androidx.compose.ui.test.AndroidComposeUiTest
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput

@OptIn(ExperimentalTestApi::class)
infix fun AndroidComposeUiTest<ComponentActivity>.tag(name: String): SemanticsMatcher =
    hasTestTag(name)

@OptIn(ExperimentalTestApi::class)
fun AndroidComposeUiTest<ComponentActivity>.tag(
    name: String,
    interaction: SemanticsNodeInteractionDsl.() -> Unit = { isDisplayed() }
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