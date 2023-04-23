package pl.ejdev.dixittogether

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.isEnabled
import androidx.compose.ui.test.runAndroidComposeUiTest
import org.junit.Test
import pl.ejdev.dixittogether.features.core.shared.GAME_COLORS
import pl.ejdev.dixittogether.features.game.view.model.GameViewModel
import pl.ejdev.dixittogether.features.game.view.screens.GameScreen
import pl.ejdev.dixittogether.features.players.domain.entities.Player
import pl.ejdev.dixittogether.features.players.view.model.PlayerViewModel

@OptIn(ExperimentalTestApi::class)
class GameScreenSpec : InstrumentalTest {
    private var playerViewModel: PlayerViewModel = PlayerViewModel()
    private val gameViewModel: GameViewModel = GameViewModel()

    @Test
    fun `Game screen has votes title`() {
        runAndroidComposeUiTest {
            content { GameScreen(it, playerViewModel.withPlayers(), gameViewModel) }
            tag("votes-title") {
                isDisplayed()
            }
        }
    }

    @Test
    fun `Game screen has current voter`() {
        runAndroidComposeUiTest {
            content { GameScreen(it, playerViewModel.withPlayers(), gameViewModel) }
            tag("current-voter") {
                isDisplayed()
            }
        }
    }

    @Test
    fun `Game screen has game card`() {
        runAndroidComposeUiTest {
            content { GameScreen(it, playerViewModel.withPlayers(), gameViewModel) }
            tags("game-card") {
                size shouldBe 5
                onEach {
                    it.layoutInfo.width shouldBe 692
                    it.layoutInfo.height shouldBe 420
                }
            }
        }
    }

    private fun PlayerViewModel.withPlayers(): PlayerViewModel = apply {
        add(Player("Emil", GAME_COLORS[0]))
        add(Player("Ola", GAME_COLORS[1]))
        add(Player("Bartosz", GAME_COLORS[2]))
        add(Player("Michał", GAME_COLORS[3]))
        add(Player("Gosia", GAME_COLORS[4]))
    }
}