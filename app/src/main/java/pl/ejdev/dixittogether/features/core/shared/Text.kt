package pl.ejdev.dixittogether.features.core.shared

import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import pl.ejdev.dixittogether.features.game.view.components.Votes

internal const val TITLE = "Dixit"

internal val shadowColor = Color(244, 212, 154)

@Composable
fun Title(
    text: String,
    size: TextUnit,
    lineHeight: TextUnit = 2.5.em,
    textAlign: TextAlign? = null,
    modifier: Modifier = Modifier
        .padding(16.dp)
        .offset(x = 8.dp, y = 8.dp)
) {
    Text(
        text = text,
        fontFamily = Garamond,
        fontWeight = FontWeight.Normal,
        textAlign = textAlign,
        fontSize = size,
        modifier = modifier,
        style = LocalTextStyle.current.merge(
            TextStyle(
                lineHeight = lineHeight,
                shadow = Shadow(
                    color = shadowColor,
                    offset = Offset(2f, 2f),
                    blurRadius = 1f
                ),
                lineHeightStyle = LineHeightStyle(
                    alignment = LineHeightStyle.Alignment.Center,
                    trim = LineHeightStyle.Trim.None
                )
            )
        )
    )
}


@Composable
@Preview(name = "TitleComponent", showBackground = true)
internal fun TitlePreview() {
    Title("Dixit", 24.sp)
}
