package attendance.domain

import attendance.model.AttendanceBook
import attendance.resources.Messages.*
import attendance.view.View
import java.time.LocalDate
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
            throw IllegalArgumentException(HOLIDAY.formattedMessage(monthDate, dayName))
        }
        //닉네임 입력기능
        view.showMessage(INPUT_NICKNAME.message())
        val nickName = view.readLine()

        if (!attendanceBook.isRightNickName(nickName)){
            throw IllegalArgumentException(WRONG_NICKNAME.errorMessage())
        }

        // 이미 출석했는지 확인하는 기능
        if (attendanceBook.isNameInBook(nickName, now)){
            throw IllegalArgumentException(READY_ATTENDANCE.errorMessage())
        }

        // 등교 시간을 입력 받고 출석 처리하는 기능
        view.showMessage(INPUT_TIME.message())
        val time = view.readLine()
        require(':' in time){WRONG_SYNTAX.errorMessage()}
        val timeSplit = time.split(':')
        timeSplit.forEach {
            require(it.length == 2){WRONG_SYNTAX.errorMessage()}
            it.toIntOrNull() ?: throw IllegalArgumentException(WRONG_SYNTAX.errorMessage())
        }
        require(timeSplit[0].toInt() in 0..23){WRONG_SYNTAX.errorMessage()}
        require(timeSplit[1].toInt() in 0..59) {WRONG_SYNTAX.errorMessage()}
        require(timeSplit[0].toInt() in 8..22 || time == "23:00") {WRONG_TIME.errorMessage()}
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