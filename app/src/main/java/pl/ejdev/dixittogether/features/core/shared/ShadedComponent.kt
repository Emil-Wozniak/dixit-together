package pl.ejdev.dixittogether.features.core.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp

@Composable
fun ShadedComponent(
    modifier: Modifier = Modifier,
    roundCornerPercentage: Int = 5,
    linearGradient: Brush = Brush.linearGradient(
        0.03f to componentColor.copy(alpha = 0.8f),
        0.1f to shadingColor,
        0.7f to componentColor,
    ),
    shadow: Int = 4,
    content: @Composable () -> Unit
) {

    val roundedCornerShape = RoundedCornerShape(roundCornerPercentage)
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .shadow(shadow.dp)
        ,
        shape = roundedCornerShape
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fadingEdge(linearGradient)
                .background(componentColor),
            shape = roundedCornerShape,
        ) {
            content()
        }

    }
}