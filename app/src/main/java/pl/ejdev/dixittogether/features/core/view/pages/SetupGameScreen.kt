package pl.ejdev.dixittogether.features.core.view.pages

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import pl.ejdev.dixittogether.features.core.shared.KeltWide
import pl.ejdev.dixittogether.features.core.shared.Title
import pl.ejdev.dixittogether.features.players.domain.entities.Player
import pl.ejdev.dixittogether.features.players.view.widgets.AddPlayer

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
                    Icon(
                        Icons.Outlined.AccountCircle,
                        contentDescription = "player icon",
                        tint = it.color?.color ?: Color.Black
                    )
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(
                        text = "${it.name}",
                        fontFamily = KeltWide,
                        fontWeight = FontWeight.Normal,
                        fontSize = 24.sp
                    )
                }
            }
        }
    }
}