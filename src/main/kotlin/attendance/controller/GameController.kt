package attendance.controller

import attendance.view.GameView
import attendance.domain.InputValidator
import attendance.domain.GameService
import attendance.model.NumberBasket
import attendance.resources.Messages.*

class GameController(
    private val gameView: GameView,
    private val validator: InputValidator,
    private val gameService: GameService
) {
    fun gameStart() {
        val numberBasket = generateNumberBasket()
        gameView.showMessage(SUM_START_HEADER.message())
        gameView.showBlankLine()

        announceSumNumbers(numberBasket)
    }

    private fun generateNumberBasket(): NumberBasket {
        val basket = NumberBasket()

        basket.addNumber(readNumberWithRetry(LEFT_VALUE_INPUT.infoMessage()))
        basket.addNumber(readNumberWithRetry(RIGHT_VALUE_INPUT.infoMessage()))

        return basket
    }

    private fun readNumberWithRetry(infoMessage: String): Int {
        while (true) {
            try {
                gameView.showMessage(infoMessage)
                return validator.validateInteger(gameView.readLine())
            } catch (e: IllegalArgumentException) {
                gameView.showMessage(e.message ?: INVALID_ERROR.errorMessage())
            }
        }
    }

    private fun announceSumNumbers(numberBasket: NumberBasket) {
        val expression = gameService.getExpression(numberBasket)
        val sumValue = gameService.plusTwoNumber(numberBasket)
        gameView.showMessage(SUM_RESULT.formattedMessage(expression, sumValue))
    }

    companion object {
        fun create(): GameController {
            val gameView = GameView()
            val inputValidator = InputValidator()
            val gameService = GameService()
            return GameController(gameView, inputValidator, gameService)
        }
    }
}