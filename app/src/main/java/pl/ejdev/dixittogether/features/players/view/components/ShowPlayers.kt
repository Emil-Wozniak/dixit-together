package pl.ejdev.dixittogether.features.players.view.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import pl.ejdev.dixittogether.features.core.shared.Island_Moments
import pl.ejdev.dixittogether.features.core.shared.KeltWide
import pl.ejdev.dixittogether.features.players.domain.entities.Player

@Composable
internal fun ShowPlayers(players: List<Player>) {
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
                    Icons.Outlined.Person,
                    contentDescription = "player icon",
                    tint = it.gameColor?.color ?: Color.Black
                )
                Spacer(modifier = Modifier.size(4.dp))
                Text(
                    text = "${it.name}",
                    fontFamily = Island_Moments,
                    fontWeight = FontWeight.Black,
                    fontSize = 6.em,
                )
            }
        }
    }
}

@Preview(name = "ShowPlayers", group = "players", backgroundColor = 0xFFFFFFFF)
@Composable
private fun ShowPlayersPreview() {
    ShowPlayers(
        listOf(
            Player("Emil"),
            Player("Ola"),
            Player("Bartosz"),
        )
    )
}