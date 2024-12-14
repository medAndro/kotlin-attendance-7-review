package attendance.domain

import attendance.model.AttendanceBook
import attendance.resources.Messages.*
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
            view.showMessage(HOLIDAY.formattedMessage(monthDate, dayName))
            return
        }

        //닉네임 입력기능
        view.showMessage(INPUT_NICKNAME.message())
        val nickName = view.readLine()

        if (!attendanceBook.isRightNickName(nickName)){
            view.showMessage(WRONG_NICKNAME.errorMessage())
            return
        }

        // 이미 출석했는지 확인하는 기능
        if (attendanceBook.isNameInBook(nickName, now)){
            view.showMessage(READY_ATTENDANCE.errorMessage())
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