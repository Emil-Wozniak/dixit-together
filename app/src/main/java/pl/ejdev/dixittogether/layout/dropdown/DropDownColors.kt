package pl.ejdev.dixittogether.layout.dropdown

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import pl.ejdev.dixittogether.layout.shape.Circle
import pl.ejdev.dixittogether.features.core.shared.GAME_COLORS
import pl.ejdev.dixittogether.features.core.shared.GameColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun DropDownColors(
    colors: List<GameColor>,
    onSelect: (color: GameColor) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var selected by remember { mutableStateOf(GAME_COLORS[0].color) }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }
    val icon =
        if (expanded) Icons.Filled.KeyboardArrowUp
        else Icons.Filled.KeyboardArrowDown

    Column(Modifier.width(48.dp)) {
        OutlinedCard {
            IconButton(
                onClick = { expanded = true },
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned { textFieldSize = it.size.toSize() },
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = "select color",
                    modifier = Modifier.clickable { expanded = !expanded }
                )
                Circle(color = selected)
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.width(with(LocalDensity.current) { textFieldSize.width.toDp() })
            ) {
                colors.forEach {
                    DropdownMenuItem(
                        text = { Circle(color = it.color) },
                        onClick = {
                            selected = it.color
                            expanded = false
                            onSelect(it)
                        })
                }
            }
        }
    }
}