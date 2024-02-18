package pl.ejdev.dixittogether.features.players.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.PersonAdd
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import pl.ejdev.dixittogether.features.core.shared.GAME_COLORS
import pl.ejdev.dixittogether.features.core.shared.GameColor
import pl.ejdev.dixittogether.features.core.shared.Island_Moments
import pl.ejdev.dixittogether.features.core.view.widgets.DixitIconButton
import pl.ejdev.dixittogether.features.core.view.widgets.DropDownColors
import pl.ejdev.dixittogether.features.players.domain.entities.Player
import pl.ejdev.dixittogether.startScreenBackground

private const val ADD_USER = "add user"
private const val ADD_USER_BTN = "add-user-btn"
private const val USER_NAME_TEXT_FIELD = "user-name-text-field"

private fun dataFilled(name: String, color: GameColor?): Boolean = color != null && name != ""

@Composable
internal fun AddPlayer(
    usedColors: List<GameColor>,
    defaultName: String = "",
    onAddUser: (player: Player) -> Unit
) {
    var player by remember { mutableStateOf(Player()) }
    var name by remember { mutableStateOf(defaultName) }
    var color by remember { mutableStateOf<Color?>(GAME_COLORS[0].color) }
    val colors by remember { mutableStateOf(GAME_COLORS.map(GameColor::color)) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(72.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        OutlinedTextField(
            value = name,
            textStyle = TextStyle.Default.copy(
                fontFamily = Island_Moments,
                fontSize = 5.em, fontWeight = FontWeight.Black
            ),
            onValueChange = { name = it },
            colors = OutlinedTextFieldDefaults.colors(
            ),
            modifier = Modifier
                .background(Color.White)
                .semantics { testTag = USER_NAME_TEXT_FIELD }
        )
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                DropDownColors(
                    colors = colors.filterNot { it in usedColors.map(GameColor::color) }
                ) {
                    color = it
                }
            }
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                DixitIconButton(
                    active = dataFilled(name, GAME_COLORS.find { it.color == color }),
                    imageVector = Icons.Rounded.PersonAdd,
                    contentDescription = ADD_USER,
                    onClick = {
                        GAME_COLORS
                            .find { it.color == color }
                            .let(::requireNotNull)
                            .let { player = Player(name, it) }
                        onAddUser(player)
                        name = defaultName
                        color = null
                    },
                    modifier = Modifier.semantics { testTag = ADD_USER_BTN }
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
internal fun AddPlayerPreview() {
    Surface(
        modifier = Modifier.size(400.dp),
        color = startScreenBackground
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            AddPlayer(listOf(), "test case") {}
        }
    }
}