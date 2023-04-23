package pl.ejdev.dixittogether.features.game.view.model

import io.kotest.core.spec.style.FeatureSpec
import io.kotest.matchers.shouldBe
import pl.ejdev.dixittogether.features.game.domain.entities.PlayerResult
import pl.ejdev.dixittogether.features.players.domain.entities.Player

private const val EMIL = "Emil"
private const val OLA = "Ola"

class GameViewModelSpec : FeatureSpec({
    feature("game view model") {
        val game = GameViewModel()
        val players = listOf(Player(EMIL), Player(OLA))
        scenario("add results for players") {
            val emilResult = PlayerResult(players[0], 1)
            val olaResult = PlayerResult(players[1], 3)
            val playerResults = listOf(emilResult, olaResult)

            game.start(players)
            game.finishRound(playerResults)
            val results = game.getPlayersResults()
            results[0].score shouldBe 1
            results[0].player.name shouldBe EMIL
            results[1].score shouldBe 3
            results[1].player.name shouldBe OLA
        }
    }
})