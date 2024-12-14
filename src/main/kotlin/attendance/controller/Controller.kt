package attendance.controller

import attendance.domain.CSVLoadService
import attendance.view.GameView
import attendance.domain.InputValidator
import attendance.domain.GameService
import attendance.model.AttendanceBook
import attendance.model.NumberBasket
import attendance.resources.AppConfig
import attendance.resources.Messages.*
import camp.nextstep.edu.missionutils.DateTimes
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

class Controller(
    private val gameView: GameView,
    private val validator: InputValidator,
    private val gameService: GameService,
    private val csvLoadService: CSVLoadService
) {
    fun start() {
        showInfoMessage()
        val attendanceBook = makeAttendanceBook()
    }

    private fun showInfoMessage() {
        val now = DateTimes.now()
        val pattern = DateTimeFormatter.ofPattern("MM월 dd일");
        val monthDate = now.format(pattern);
        val dayName = now.dayOfWeek.getDisplayName(TextStyle.NARROW, Locale.KOREAN)
        gameView.showMessage(START_INFO.formattedMessage(monthDate, dayName))
    }

    private fun makeAttendanceBook(): AttendanceBook{
        val attendBook = AttendanceBook()
        attendBook.addByPair(csvLoadService.loadAttendance(AppConfig.CSV_FILE_LOCATION.value))
        return attendBook
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

//    private fun announceSumNumbers(numberBasket: NumberBasket) {
//        val expression = gameService.getExpression(numberBasket)
//        val sumValue = gameService.plusTwoNumber(numberBasket)
//        gameView.showMessage(SUM_RESULT.formattedMessage(expression, sumValue))
//    }

    companion object {
        fun create(): Controller {
            val gameView = GameView()
            val inputValidator = InputValidator()
            val gameService = GameService()
            val csvLoadService = CSVLoadService()
            return Controller(gameView, inputValidator, gameService, csvLoadService)
        }
    }
}