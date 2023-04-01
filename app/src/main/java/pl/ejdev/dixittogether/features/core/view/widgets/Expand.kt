package pl.ejdev.dixittogether.features.core.view.widgets

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun Expand(
    children: @Composable (
        expand: Boolean,
        icon: ImageVector,
        onClick: (value: Boolean) -> Unit,
    ) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val icon =
        if (expanded) Icons.Filled.KeyboardArrowUp
        else Icons.Filled.KeyboardArrowDown

    children(expanded, icon) { value: Boolean -> expanded = value }
}