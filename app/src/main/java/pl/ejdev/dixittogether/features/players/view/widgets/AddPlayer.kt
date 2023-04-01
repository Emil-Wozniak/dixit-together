package pl.ejdev.dixittogether.features.players.view.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.unit.dp
import pl.ejdev.dixittogether.features.core.shared.GAME_COLORS
import pl.ejdev.dixittogether.features.core.shared.GameColor
import pl.ejdev.dixittogether.features.core.view.widgets.DropDownColors
import pl.ejdev.dixittogether.features.players.domain.entities.Player
import pl.ejdev.dixittogether.features.players.data.sources.playerSaver

private const val ADD_USER = "add user"
private const val ADD_USER_BTN = "add-user-btn"
private const val ADD_USER_ICON = "add-user-icon"
private const val USER_NAME_TEXT_FIELD = "user-name-text-field"

private fun dataFilled(
    name: MutableState<String>,
    color: MutableState<GameColor?>
): Boolean =
    color.value != null && name.value != ""
@Composable
internal fun AddPlayer(
    usedColors: List<GameColor>,
    onAddUser: (MutableState<Player>) -> Unit
) {
    val player: MutableState<Player> = rememberSaveable(stateSaver = playerSaver) {
        mutableStateOf(Player())
    }

    val name = remember { mutableStateOf("") }
    val color = remember { mutableStateOf<GameColor?>(GAME_COLORS[0]) }
    val colors = GAME_COLORS
    Column {
        Row(modifier = Modifier.fillMaxWidth()) {
            Column(Modifier.fillMaxWidth(0.5f)) {
                OutlinedTextField(
                    value = name.value,
                    onValueChange = { name.value = it },
                    modifier = Modifier
                        .background(Color.White)
                        .semantics { testTag = USER_NAME_TEXT_FIELD }
                )
            }
            Spacer(modifier = Modifier.size(4.dp))
            DropDownColors(colors = colors.filter { it !in usedColors }) {
                color.value = it
            }
            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
            Button(
                enabled = dataFilled(name, color),
                onClick = {
                    player.value = Player(name.value, color.value)
                    name.value = ""
                    color.value = null
                    onAddUser(player)
                },
                contentPadding = PaddingValues(
                    start = 12.dp,
                    top = 12.dp,
                    end = 12.dp,
                    bottom = 12.dp
                ),
                modifier = Modifier.semantics {
                    testTag = ADD_USER_BTN
                }
            ) {
                Icon(
                    Icons.Filled.Add,
                    contentDescription = ADD_USER,
                    modifier = Modifier.semantics {
                        testTag = ADD_USER_ICON
                    }
                )
            }
        }
    }
}