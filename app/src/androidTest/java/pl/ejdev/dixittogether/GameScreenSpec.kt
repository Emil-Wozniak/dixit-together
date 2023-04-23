package pl.ejdev.dixittogether

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.runAndroidComposeUiTest
import br.com.colman.kotest.FreeSpec
import io.kotest.matchers.shouldBe
import pl.ejdev.dixittogether.features.core.shared.GAME_COLORS
import pl.ejdev.dixittogether.features.game.view.model.GameViewModel
import pl.ejdev.dixittogether.features.game.view.screens.GameScreen
import pl.ejdev.dixittogether.features.players.domain.entities.Player
import pl.ejdev.dixittogether.features.players.view.model.PlayerViewModel

private fun PlayerViewModel.withPlayers() = apply {
    add(Player("Emil", GAME_COLORS[0]))
    add(Player("Ola", GAME_COLORS[1]))
    add(Player("Bartosz", GAME_COLORS[2]))
    add(Player("Michał", GAME_COLORS[3]))
    add(Player("Gosia", GAME_COLORS[4]))
}

@OptIn(ExperimentalTestApi::class)
class GameScreenSpec : FreeSpec({
    val playerViewModel = PlayerViewModel()
    val gameViewModel = GameViewModel()

    beforeAny { playerViewModel.withPlayers() }
    afterAny { playerViewModel.clear() }

    "Game screen has votes title" - {
        runAndroidComposeUiTest {
            content { GameScreen(it, playerViewModel, gameViewModel) }
            tag(name = "votes-title")
        }
    }
    "Game screen has current voter" - {
        runAndroidComposeUiTest {
            content { GameScreen(it, playerViewModel, gameViewModel) }
            tag(name = "current-voter")
        }
    }
    "Game screen has game cards" - {
        runAndroidComposeUiTest {
            content { GameScreen(it, playerViewModel, gameViewModel) }
            tags("game-card") {
                size shouldBe 5
                onEach {
                    it.layoutInfo.width shouldBe 692
                    it.layoutInfo.height shouldBe 420
                }
            }
        }
    }
})


