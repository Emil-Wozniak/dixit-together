package pl.ejdev.dixittogether.features.core.view.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.PersonAdd
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pl.ejdev.dixittogether.features.core.shared.componentColor
import pl.ejdev.dixittogether.features.core.shared.fadingEdge
import pl.ejdev.dixittogether.startScreenBackground

@Composable
internal fun DixitIconButton(
    active: Boolean = true,
    imageVector: ImageVector,
    contentDescription: String?,
    size: Int = 48,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    content: @Composable () -> Unit = {},
) {
    val linearGradient = Brush.linearGradient(
        0.1f to componentColor.copy(alpha = 0.8f),
        0.7f to componentColor,
    )
    val roundedCornerShape = RoundedCornerShape(50)
    Surface(
        modifier = Modifier
            .size(size.dp),
        shape = roundedCornerShape,
    ) {
        Surface(
            modifier = Modifier
                .size((size -2).dp)
                .fadingEdge(linearGradient)
                .background(componentColor),
            shape = roundedCornerShape,
        ) {
            IconButton(
                onClick = onClick,
                enabled = active,
                modifier = Modifier.fillMaxSize().background(componentColor)
            ) {
                Icon(
                    imageVector = imageVector,
                    contentDescription = contentDescription,
                    modifier = modifier,
                    tint = Color.White
                )
                content()
            }
        }
    }
}

@Preview
@Composable
internal fun DixitIconButtonPreview() {
    Surface(
        modifier = Modifier.size(200.dp),
        color = startScreenBackground
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            DixitIconButton(imageVector = Icons.Rounded.PersonAdd, contentDescription = "")
        }
    }
}