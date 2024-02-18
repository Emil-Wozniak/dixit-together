package pl.ejdev.dixittogether.features.core.shared

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer

val componentColor = Color.hsv(0.59f, 0.58f, 0.08f)
val shadingColor = Color(245, 245, 245)

fun Modifier.fadingEdge(brush: Brush) = this
    .graphicsLayer(compositingStrategy = CompositingStrategy.Offscreen)
    .drawWithContent {
        drawContent()
        drawRect(brush = brush, blendMode = BlendMode.DstIn)
    }

/**
 * 0f to Color.Transparent, 0.3f to Color.Red
 */
fun topFade(
    vararg colorStops: Pair<Float, Color>,
) = Brush.verticalGradient(*colorStops)

/**
 * 0f to Color.Transparent,
 * 0.3f to Color.Red,
 * 0.7f to Color.Red,
 * 1f to Color.Transparent
 */
fun topBottomFade(
    vararg colorStops: Pair<Float, Color>,
) = Brush.verticalGradient(*colorStops)

val leftRightFade = Brush.horizontalGradient(
    0f to Color.Transparent, // left
    0.1f to Color.Red,
    0.9f to Color.Red,
    1f to Color.Transparent // right
)

val radialFade = Brush.radialGradient(
    0f to Color.Red,
    0.5f to Color.Transparent
)