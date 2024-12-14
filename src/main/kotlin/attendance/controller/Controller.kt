package attendance.controller

import attendance.domain.CSVLoadService
import attendance.view.View
import attendance.domain.InputValidator
import attendance.domain.Service
import attendance.model.AttendanceBook
import attendance.resources.AppConfig
import attendance.resources.Messages.*
import camp.nextstep.edu.missionutils.DateTimes
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

class Controller(
    private val view: View,
    private val validator: InputValidator,
    private val service: Service,
    private val csvLoadService: CSVLoadService
) {
    private var now = DateTimes.now()
    fun start() {
        val attendanceBook = makeAttendanceBook()
        while(true){
            val selectedMenu = readSelectWithRetry()
            val isQuit = goMenu(selectedMenu, attendanceBook)
            if (isQuit) {
                break
            }
        }
    }

    private fun showInfoMessage():String {
        now = DateTimes.now()
//        now = now.minusDays(1)
        val pattern = DateTimeFormatter.ofPattern("MM월 dd일");
        val monthDate = now.format(pattern);
        val dayName = now.dayOfWeek.getDisplayName(TextStyle.NARROW, Locale.KOREAN)
        view.showMessage(START_INFO.formattedMessage(monthDate, dayName))

        return "${monthDate} ${dayName}요일"
    }

    private fun makeAttendanceBook(): AttendanceBook{
        val attendBook = AttendanceBook()
        attendBook.addByPair(csvLoadService.loadAttendance(AppConfig.CSV_FILE_LOCATION.value))
        return attendBook
    }

    private fun readSelectWithRetry(): String {
        while (true) {
            try {
                val dateText = showInfoMessage()
                return validator.validateSelect(view.readLine(), dateText)
            } catch (e: IllegalArgumentException) {
                view.showMessage(e.message ?: INVALID_ERROR.errorMessage())
            }
        }
    }

    private fun goMenu(menuText: String, attendanceBook: AttendanceBook):Boolean{
        try {
            if (menuText == "1"){
                service.menu1(now, attendanceBook)
            }
            if (menuText == "2"){
                service.menu2(now, attendanceBook)
            }
            if (menuText == "3"){
                service.menu3(attendanceBook)
            }
            if (menuText == "4"){
                service.menu4()
            }
        } catch (e: IllegalArgumentException) {
            view.showMessage(e.message ?: INVALID_ERROR.errorMessage())
        }
        if (menuText == "Q"){
            return true
        }
        return false
    }

    companion object {
        fun create(): Controller {
            val view = View()
            val inputValidator = InputValidator()
            val service = Service(view)
            val csvLoadService = CSVLoadService()
            return Controller(view, inputValidator, service, csvLoadService)
        }
    }
}