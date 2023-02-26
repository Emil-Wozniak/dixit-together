package pl.ejdev.dixittogether.layout.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.navigation.NavHostController
import pl.ejdev.dixittogether.entity.Player
import pl.ejdev.dixittogether.entity.playerSaver
import pl.ejdev.dixittogether.layout.dropdown.DropDownColors
import pl.ejdev.dixittogether.layout.shape.Circle
import pl.ejdev.dixittogether.shared.GAME_COLORS
import pl.ejdev.dixittogether.shared.GameColor
import pl.ejdev.dixittogether.shared.KeltWide
import pl.ejdev.dixittogether.shared.Title

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
                )
            ) {
                Icon(
                    Icons.Filled.Add,
                    contentDescription = "add user"
                )
            }
        }
    }
}

private fun dataFilled(
    name: MutableState<String>,
    color: MutableState<GameColor?>
): Boolean =
    color.value != null && name.value != ""

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SetupGameScreen(navController: NavHostController) {
    val players = remember { mutableStateListOf<Player>() }
    View(navController = navController) {
        Title(
            text = "Players:",
            size = 14.em,
            lineHeight = 1.em,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 8.dp)
        )
        AddPlayer(usedColors = players.mapNotNull(Player::color)) {
            players.add(it.value)
        }
        OutlinedCard(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            players.map {
                Row(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                ) {
                    it.color?.let { Circle(color = requireNotNull(it.color)) }
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(
                        text = "${it.name}",
                        fontFamily = KeltWide,
                        fontWeight = FontWeight.Normal,
                    )
                }
            }
        }
    }
}