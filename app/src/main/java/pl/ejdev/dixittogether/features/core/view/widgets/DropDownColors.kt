package pl.ejdev.dixittogether.features.core.view.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import pl.ejdev.dixittogether.features.core.shared.GAME_COLORS
import pl.ejdev.dixittogether.features.core.shared.GameColor

@Composable
internal fun DropDownColors(
    colors: List<GameColor>,
    onSelect: (color: GameColor) -> Unit
) {
    var selected by remember { mutableStateOf(GAME_COLORS[0].color) }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }
    Expand { expanded, icon, onClick ->
        Column(Modifier.width(48.dp)) {
            OutlinedCard {
                IconButton(
                    onClick = { onClick(!expanded) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .onGloballyPositioned { textFieldSize = it.size.toSize() },
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = "select color",
                        modifier = Modifier.clickable { onClick(!expanded) }
                    )
                    Circle(color = selected)
                }

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { onClick(false) },
                    modifier = Modifier.width(with(LocalDensity.current) { textFieldSize.width.toDp() })
                ) {
                    colors.forEach {
                        DropdownMenuItem(
                            text = { Circle(color = it.color) },
                            onClick = {
                                selected = it.color
                                onClick(false)
                                onSelect(it)
                            })
                    }
                }
            }
        }
    }
}