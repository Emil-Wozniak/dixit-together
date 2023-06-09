package pl.ejdev.dixittogether.features.core.view.widgets

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.em
import pl.ejdev.dixittogether.features.core.shared.KeltWide

private val buttonBg =
    Color(174, 31, 27)

@Composable
internal fun GameButton(
    text: String,
    active: Boolean = true,
    size: TextUnit = 6.em,
    onClick: () -> Unit
) = Button(
    onClick = onClick,
    enabled = active,
    colors = buttonColors(
        containerColor = buttonBg,
        contentColor = colorScheme.surface
    )
) {
    Text(
        text,
        fontFamily = KeltWide,
        fontSize = size,
        color = Color.White
    )
}

@Preview
@Composable
internal fun GameButtonPreview() {
    GameButton(text = "Start") {
    }
}

