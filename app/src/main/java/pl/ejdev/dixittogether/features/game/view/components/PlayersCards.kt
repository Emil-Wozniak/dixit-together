package pl.ejdev.dixittogether.features.game.view.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardDefaults.cardElevation
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import pl.ejdev.dixittogether.features.core.shared.GAME_COLORS
import pl.ejdev.dixittogether.features.core.shared.ShadedComponent
import pl.ejdev.dixittogether.features.core.shared.Title
import pl.ejdev.dixittogether.features.core.shared.componentColor
import pl.ejdev.dixittogether.features.game.domain.entities.PlayerResult
import pl.ejdev.dixittogether.features.game.view.model.GameViewModel
import pl.ejdev.dixittogether.features.players.domain.entities.Player
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun PlayersCards(
    currentVoter: Color?,
    cardsVotes: SnapshotStateMap<Color, MutableList<Color>>,
    gameViewModel: GameViewModel,
    vote: (currentVoter: Color, cardColor: Color) -> Unit
) {
    val playersResults: List<PlayerResult> = gameViewModel.getPlayersResults()
    val pagerState = rememberPagerState(pageCount = { Int.MAX_VALUE })
    val currentPageOffset = pagerState.currentPageOffsetFraction
    val currentIndex = pagerState.currentPage
    val maxOffset = 70.dp
    if (playersResults.isNotEmpty()) {
        ShadedComponent(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(componentColor)
                    .padding(8.dp)
            ) {
                CompositionLocalProvider(LocalOverscrollConfiguration provides null) {
                    HorizontalPager(
                        state = pagerState,
                        pageSize = PageSize.Fixed(155.dp),
                        pageSpacing = 5.dp,
                        contentPadding = PaddingValues(horizontal = 10.dp),
                        modifier = Modifier.fillMaxSize(),
                    ) { page ->
                        val playerResult = playersResults.getOrNull(page % playersResults.size)
                        if (playerResult != null) {
                            val color = playerResult.player.getColorOrDefault()
                            val offset = maxOffset * when (page) {
                                currentIndex -> currentPageOffset.absoluteValue
                                currentIndex - 1 -> 1 + currentPageOffset.coerceAtMost(0f)
                                currentIndex + 1 -> 1 - currentPageOffset.coerceAtLeast(0f)
                                else -> 1f
                            }
                            val isCurrent = offset != 0.dp
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(4.dp)
                                    .size(250.dp)
                                    .clickable { currentVoter?.let { vote(it, color) } }
                                    .semantics { testTag = "game-card-${page}" }
                                    .border(
                                        width = 6.dp,
                                        color =
                                        if (isCurrent) Color.White.copy(alpha = 0.7f)
                                        else Color.White,
                                        shape = RoundedCornerShape(percent = 4)
                                    ),
                                elevation = cardElevation(defaultElevation = 4.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = Color.LightGray
                                )
                            ) {
                                GameCard(color, cardsVotes[color])
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun GameCard(color: Color, votes: MutableList<Color>?) {
    CardOwner(color)
    CardTitle()
    CardVotes(votes)
}

@Composable
private fun CardVotes(votes: MutableList<Color>?) {
    Row(modifier = Modifier.padding(start = 8.dp)) {
        votes?.forEach { addedColor ->
            Surface(
                modifier = Modifier
                    .size(12.dp)
                    .padding(1.dp),
                shape = CircleShape,
                color = addedColor
            ) {}
        }
    }
}

@Composable
private fun CardOwner(color: Color) {
    Column(
        modifier = Modifier
            .padding(6.dp)
            .height(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = CenterHorizontally,
    ) {
        Surface(
            modifier = Modifier
                .width(15.dp)
                .height(11.dp)
                .padding(start = 4.dp, top = 4.dp, end = 4.dp)
                .shadow(4.dp, shape = CircleShape),
            color = color,
            shape = RoundedCornerShape(4.dp)
        ) {}
        Surface(
            modifier = Modifier
                .width(20.dp)
                .height(20.dp)
                .padding(start = 4.dp, bottom = 4.dp, end = 4.dp)
                .shadow(4.dp, shape = RoundedCornerShape(topEnd = 6.dp, topStart = 6.dp)),
            color = color,
            shape = RoundedCornerShape(topEnd = 6.dp, topStart = 6.dp)
        ) {}
    }
}

@Composable
private fun CardTitle() {
    Column(
        horizontalAlignment = CenterHorizontally,
        modifier = Modifier
            .padding(30.dp)
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center)
    ) {
        Title(text = "Dixit", 24.sp)
    }
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Preview(name = "Players cards", showBackground = true)
@Composable
internal fun PlayersCardsPreview() {
    val cardsVotes: SnapshotStateMap<Color, MutableList<Color>> = remember {
        mutableStateMapOf()
    }
    val from = GAME_COLORS.mapIndexed { index, gameColor ->
        gameColor.color to GAME_COLORS
            .filter { it.color != gameColor.color }
            .take(index + 1)
            .map { it.color }.toMutableList()
    }.toMap()
    cardsVotes.putAll(from)
    val gameViewModel = GameViewModel().apply {
        GAME_COLORS.mapIndexed { index, gameColor ->
            Player("player $index", gameColor)
        }.let { start(it) }
    }

    PlayersCards(GAME_COLORS[4].color, cardsVotes, gameViewModel) { _, _ -> }
}
