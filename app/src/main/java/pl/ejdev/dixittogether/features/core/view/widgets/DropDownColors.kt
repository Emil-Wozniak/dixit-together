package pl.ejdev.dixittogether.features.core.view.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import pl.ejdev.dixittogether.features.core.shared.GAME_COLORS
import pl.ejdev.dixittogether.features.core.shared.componentColor
import pl.ejdev.dixittogether.startScreenBackground

@Composable
internal fun DropDownColors(
    colors: List<Color>,
    modifier: Modifier = Modifier,
    onSelect: (color: Color) -> Unit
) {
    var selected by remember { mutableStateOf(GAME_COLORS[0].color) }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }
    Expand { expanded, icon, onClick ->
        Column(
            modifier = modifier
                .width(48.dp)
        ) {
            DixitIconButton(
                onClick = { onClick(!expanded) },
                imageVector = icon,
                contentDescription = "select color",
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned { textFieldSize = it.size.toSize() },
            ) {
                Circle(color = selected)
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { onClick(false) },
                modifier = Modifier
                    .width(with(LocalDensity.current) { textFieldSize.width.toDp() })
                    .background(componentColor)
            ) {
                colors.forEach {
                    DropdownMenuItem(
                        text = { Circle(color = it) },
                        onClick = {
                            selected = it
                            onClick(false)
                            onSelect(it)
                        }
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
internal fun DropDownColorsPreview() {
    Surface(
        modifier = Modifier.size(200.dp),
        color = startScreenBackground
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            DropDownColors(
                GAME_COLORS.map { it.color },
            ) {}
        }
    }
}