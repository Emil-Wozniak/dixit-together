package pl.ejdev.dixittogether.layout.shape

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Circle(color: Color) {
    Canvas(
        modifier = Modifier.size(24.dp),
        onDraw = { drawCircle(color = color) }
    )
}