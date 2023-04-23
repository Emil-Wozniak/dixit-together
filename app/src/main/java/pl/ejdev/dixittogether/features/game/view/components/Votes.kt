package pl.ejdev.dixittogether.features.game.view.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import pl.ejdev.dixittogether.features.core.shared.Title

@Composable
internal fun Votes(votes: List<Color>) {
    Row(modifier = Modifier.padding(4.dp)) {
        Title(
            "Votes:", size = 6.em, modifier = Modifier
                .padding(0.dp)
                .offset(0.dp, 0.dp)
        )
        votes.map { color ->
            Card(
                modifier = Modifier
                    .width(20.dp)
                    .height(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = color,
                    contentColor = MaterialTheme.colorScheme.surface
                ),
            ) { }
        }
    }
}