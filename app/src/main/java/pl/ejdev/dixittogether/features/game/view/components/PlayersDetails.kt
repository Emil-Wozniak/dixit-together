package pl.ejdev.dixittogether.features.game.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import pl.ejdev.dixittogether.features.core.shared.GAME_COLORS
import pl.ejdev.dixittogether.features.core.shared.Island_Moments
import pl.ejdev.dixittogether.features.core.shared.componentColor
import pl.ejdev.dixittogether.features.core.shared.fadingEdge
import pl.ejdev.dixittogether.features.core.shared.shadingColor
import pl.ejdev.dixittogether.features.core.view.pages.View
import pl.ejdev.dixittogether.features.game.domain.entities.PlayerDetails
import pl.ejdev.dixittogether.features.game.view.model.GameViewModel
import pl.ejdev.dixittogether.features.players.domain.entities.Player

@Composable
internal fun PlayersDetails(gameViewModel: GameViewModel) {
    val borderColor = shadingColor
    val dividerHeight = Modifier.height(24.dp)
    val linearGradient = Brush.linearGradient(
        0.03f to componentColor.copy(alpha = 0.8f),
        0.1f to shadingColor,
        0.7f to componentColor,
    )
    val roundedCornerShape = RoundedCornerShape(5)
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(4.dp),
        shape = roundedCornerShape
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fadingEdge(linearGradient)
                .background(componentColor),
            shape = roundedCornerShape,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(componentColor)
                    .padding(8.dp)
                    .verticalScroll(rememberScrollState())
                    .background(componentColor)
            ) {
                TableHeader(dividerHeight, borderColor)
                HorizontalDivider(color = borderColor)
                PlayersResultsRows(dividerHeight, borderColor, gameViewModel)
            }
        }
    }
}

@Composable
private fun PlayersResultsRows(
    modifier: Modifier,
    borderColor: Color,
    gameViewModel: GameViewModel = viewModel(),
) {
    gameViewModel.getPlayerDetails().map { player ->
        Row(modifier = Modifier.fillMaxWidth()) {
            PlayerIcon(player)
            VerticalDivider(
                modifier = modifier,
                thickness = 1.dp,
                color = borderColor
            )
            Text(
                text = player.name,
                fontFamily = Island_Moments,
                fontWeight = FontWeight.Black,
                color = shadingColor,
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .padding(start = 4.dp, end = 4.dp)
            )
            VerticalDivider(
                modifier = modifier,
                thickness = 1.dp,
                color = borderColor
            )
            Text(
                text = "${player.score}",
                fontFamily = Island_Moments,
                fontWeight = FontWeight.Black,
                color = shadingColor,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 4.dp)
            )
        }
        HorizontalDivider(color = borderColor)
    }
}

@Composable
private fun TableHeader(
    modifier: Modifier,
    borderColor: Color,
) {
    Row {
        Box(
            modifier = Modifier
                .padding(end = 4.dp)
                .height(24.dp)
                .width(24.dp),
        )
        VerticalDivider(
            modifier = modifier,
            thickness = 1.dp,
            color = borderColor
        )
        Text(
            text = "Player",
            fontFamily = Island_Moments,
            fontWeight = FontWeight.Black,
            color = shadingColor,
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .padding(start = 4.dp, end = 4.dp)
        )
        VerticalDivider(
            modifier = modifier,
            thickness = 1.dp,
            color = borderColor
        )
        Text(
            text = "Score",
            fontFamily = Island_Moments,
            fontWeight = FontWeight.Black,
            color = shadingColor,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 4.dp)
        )
    }
}

@Composable
private fun PlayerIcon(player: PlayerDetails) {
    Surface(
        modifier = Modifier
            .padding(end = 4.dp)
            .height(24.dp)
            .width(24.dp),
        shape = CircleShape,
    ) {
        Icon(
            Icons.Outlined.Person,
            contentDescription = "player icon",
            tint = player.color,
            modifier = Modifier.background(componentColor)
        )
    }
}

@Composable
@Preview(name = "Players details", showBackground = true)
fun PlayersDetailsPreview() {
    val navHostController = rememberNavController()
    val gameViewModel = GameViewModel().apply {
        GAME_COLORS.mapIndexed { index, gameColor ->
            Player("Gamer $index", gameColor)
        }.let { start(it) }
    }

    View(navController = navHostController) {
        PlayersDetails(gameViewModel)
    }
}