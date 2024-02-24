package pl.ejdev.dixittogether.features.core.view.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush.Companion.linearGradient
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import pl.ejdev.dixittogether.features.core.shared.KeltWide
import pl.ejdev.dixittogether.features.core.shared.componentColor
import pl.ejdev.dixittogether.features.core.shared.fadingEdge
import pl.ejdev.dixittogether.features.core.shared.shadingColor

@Composable
internal fun DixitButton(
    text: String,
    active: Boolean = true,
    size: TextUnit = 6.em,
    width: Int = 210,
    height: Int = 60,
    innerDeduct: Int = 10,
    onClick: () -> Unit = {}
) {
    val linearGradient = linearGradient(
        0.03f to componentColor.copy(alpha = 0.8f),
        0.1f to shadingColor,
        0.7f to componentColor,
    )
    val roundedCornerShape = RoundedCornerShape(20)
    Surface(
        modifier = Modifier
            .width(width.dp)
            .height(height.dp),
        shape = roundedCornerShape,
    ) {
        Surface(
            modifier = Modifier
                .width((width - innerDeduct).dp)
                .height((height - innerDeduct).dp)
                .fadingEdge(linearGradient)
                .background(componentColor),
            shape = roundedCornerShape,
        ) {
            OutlinedButton(
                onClick = onClick,
                enabled = active,
                shape = roundedCornerShape,
                colors = buttonColors(
                    containerColor = componentColor,
                ),
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text,
                    fontFamily = KeltWide,
                    fontSize = size,
                    color = if (active) Color.White else Color.Black
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
internal fun GameButtonPreview() {
    Surface(
        modifier = Modifier
            .width(400.dp)
            .height(300.dp)
            .background(Color.White)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            DixitButton(text = "Start", active = false)
            DixitButton(text = "Start", active = true)
        }
    }
}

