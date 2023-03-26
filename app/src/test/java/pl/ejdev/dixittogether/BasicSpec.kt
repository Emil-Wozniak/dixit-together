package pl.ejdev.dixittogether

import io.kotest.core.spec.style.FreeSpec
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class MyTests : FreeSpec({
    "Addition" - {
        listOf(
            row("1 + 0", 1) { 1 + 0 },
            row("1 + 1", 2) { 1 + 1 }
        ).map { (description: String, expected: Int, math: () -> Int) ->
            description {
                math() shouldBe expected
            }
        }
    }
})