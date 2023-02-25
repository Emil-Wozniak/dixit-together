package pl.ejdev.dixittogether.shared

import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import pl.ejdev.dixittogether.R

internal const val TITLE = "Dixit"
internal const val SUB_TITLE = "Together"

internal val shadowColor = Color(244,212, 154)

@OptIn(ExperimentalTextApi::class)
@Composable
fun Title(text: String, size: TextUnit, lineHeight: TextUnit = 2.5.em) {
    Text(
        text = text,
        fontFamily = KeltWide,
        fontWeight = FontWeight.Normal,
        fontSize = size,
        modifier = Modifier
            .padding(16.dp)
            .offset(x = 8.dp, y = 8.dp),
        style = LocalTextStyle.current.merge(
            TextStyle(
                lineHeight = lineHeight,
                shadow = Shadow(
                    color = shadowColor,
                    offset = Offset(8f, 8f),
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