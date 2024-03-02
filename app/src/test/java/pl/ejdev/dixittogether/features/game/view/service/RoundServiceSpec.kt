package pl.ejdev.dixittogether.features.game.view.service

import androidx.compose.ui.graphics.Color
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.ints.shouldBeExactly
import pl.ejdev.dixittogether.features.core.shared.GAME_COLORS
import pl.ejdev.dixittogether.features.core.shared.GameColor
import pl.ejdev.dixittogether.features.game.domain.entities.PlayerResult
import pl.ejdev.dixittogether.features.game.view.service.Range.Companion.card
import pl.ejdev.dixittogether.features.players.domain.entities.Player

private fun createResults() = GAME_COLORS
    .mapIndexed { index, gameColor -> makePlayer(gameColor, index) }
    .map(::makeResult)

private fun makePlayer(gameColor: GameColor, index: Int): Player = Player(
    name = "player $index",
    gameColor = gameColor,
    narrator = index == 0
)

private data class Range(val index: Int, val from: Int, val to: Int) {
    companion object {

        infix fun Int.card(votes: Pair<Int, Int>) =
            Range(this, votes.first, votes.second)
    }
}

private fun createCardVotes(vararg ranges: Range): Map<Color, List<Color>> =
    ranges.map { range ->
        GAME_COLORS[range.index].color to GAME_COLORS
            .drop(range.from)
            .take(range.to)
            .map(GameColor::color)
    }.toMap()

private fun makeResult(player: Player): PlayerResult = PlayerResult(
    player = player,
    score = 0
)

class RoundServiceSpec : FreeSpec({
    "all players receives 3 points for every vote when no one voted narrator card" - {
        // given
        val results = createResults()
        val cardsVotes = createCardVotes(
            // vote card
            1 card (1 to 3),
            2 card (4 to 1),
            3 card (5 to 1)
        )

        val playerResults = roundService(results, cardsVotes)

        // then
        with(playerResults) {
            // narrator
            get(0).score shouldBeExactly 0
            get(1).score shouldBeExactly 9
            get(2).score shouldBeExactly 3
            get(3).score shouldBeExactly 3
            get(4).score shouldBeExactly 0
            get(5).score shouldBeExactly 0
        }
    }
})

//internal class RoundServiceTest {
//    @Test
//    fun `all players receives 3 points for every vote when no one voted narrator card`() {
//        // given
//        val results = createResults()
//        val cardsVotes = mapOf(
//            // vote card            // votes
//            GAME_COLORS[1].color to GAME_COLORS.drop(1).take(3).map(GameColor::color),
//            GAME_COLORS[2].color to GAME_COLORS.drop(4).take(1).map(GameColor::color),
//            GAME_COLORS[3].color to GAME_COLORS.drop(5).take(1).map(GameColor::color),
//        )
//
//        // when
//        val playerResults = roundService(results, cardsVotes)
//
//        // then
//        with(playerResults) {
//            // narrator
//            get(0).score shouldBeExactly 0
//            get(1).score shouldBeExactly 9
//            get(2).score shouldBeExactly 3
//            get(3).score shouldBeExactly 3
//            get(4).score shouldBeExactly 0
//            get(5).score shouldBeExactly 0
//        }
//    }
//
//    @Test
//    fun `all players receives 3 points for every vote when one player did not vote narrator card`() {
//        // given
//        val results = createResults()
//        val cardsVotes = mapOf(
//            // vote card            // votes
//            GAME_COLORS[0].color to GAME_COLORS.drop(1).take(4).map(GameColor::color),
//            GAME_COLORS[2].color to GAME_COLORS.drop(5).take(1).map(GameColor::color),
//        )
//
//        // when
//        val playerResults = roundService(results, cardsVotes)
//
//        // then
//        with(playerResults) {
//            // narrator
//            get(0).score shouldBeExactly 12
//            get(1).score shouldBeExactly 0
//            get(2).score shouldBeExactly 3
//            get(3).score shouldBeExactly 0
//            get(4).score shouldBeExactly 0
//            get(5).score shouldBeExactly 0
//        }
//    }
//
//    @Test
//    fun `all players receives 2 points for every vote when all player voted narrator card`() {
//        // given
//        val results = createResults()
//        val cardsVotes = mapOf(
//            // vote card            // votes
//            GAME_COLORS[0].color to GAME_COLORS.drop(1).take(5).map(GameColor::color),
//        )
//
//        // when
//        val playerResults = roundService(results, cardsVotes)
//
//        // then
//        with(playerResults) {
//            // narrator
//            get(0).score shouldBeExactly 0
//            get(1).score shouldBeExactly 2
//            get(2).score shouldBeExactly 2
//            get(3).score shouldBeExactly 2
//            get(4).score shouldBeExactly 2
//            get(5).score shouldBeExactly 2
//        }
//    }
//}

