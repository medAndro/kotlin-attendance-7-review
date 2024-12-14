package attendance.domain

import attendance.model.AttendanceBook
import attendance.resources.Messages
import attendance.resources.Messages.WRONG_SYNTAX
import attendance.view.View
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.*

class Service(
    private val view: View
) {
    fun menu1(now: LocalDateTime, attendanceBook: AttendanceBook) {
        val pattern = DateTimeFormatter.ofPattern("MM월 dd일");
        val monthDate = now.format(pattern);
        val dayName = now.dayOfWeek.getDisplayName(TextStyle.NARROW, Locale.KOREAN)
        if (dayName == "토" || dayName == "일"){
            view.showMessage(Messages.HOLIDAY.formattedMessage(monthDate, dayName))
            return
        }
    }

    fun menu2() {
        println("메뉴2")
    }

    fun menu3() {
        println("메뉴3")
    }

    fun menu4() {
        println("메뉴4")
    }
}