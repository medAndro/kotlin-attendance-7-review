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

        val pattern2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        val year_Month_Date = now.format(pattern2);
        val attendDate = LocalDateTime.parse("${year_Month_Date} $time", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
        attendanceBook.addAttendanceDate(nickName, attendDate)
        view.showMessage("\n${monthDate} ${dayName}요일 $time (출석)\n")

    }

    fun menu2(now: LocalDateTime, attendanceBook: AttendanceBook) {
        // 출석을 수정하려는 닉네임 입력
        view.showMessage(INPUT_EDIT_NICKNAME.message())
        val nickName = view.readLine()

        if (!attendanceBook.isRightNickName(nickName)){
            throw IllegalArgumentException(WRONG_NICKNAME.errorMessage())
        }

        // 출석하지 않으면 수정할 수 없음
        if (!attendanceBook.isNameInBook(nickName, now)){
            throw IllegalArgumentException(CANT_EDIT.errorMessage())
        }

        val pattern = DateTimeFormatter.ofPattern("dd");
        val nowDate = now.format(pattern).toInt()

        view.showMessage(INPUT_EDIT_DATE.message())

        //수정하려는 날짜 입력
        val inputDate = view.readLine()

        require(nowDate >= inputDate.toInt()){
            CANT_EDIT.errorMessage()
        }

        //시간입력기능
        view.showMessage(INPUT_EDIT_TIME.message())
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

        val pattern2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        val year_Month_Date = now.format(pattern2);
        val attendDate = LocalDateTime.parse("${year_Month_Date} $time", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
        val beforeDate = attendanceBook.editAttendanceDate(nickName, attendDate)
//        view.showMessage(beforeDate.toString())
//        view.showMessage(attendDate.toString())


        val mmddpattern = DateTimeFormatter.ofPattern("MM월 dd일");
        val monthDate = now.format(mmddpattern);
        val dayName = now.dayOfWeek.getDisplayName(TextStyle.NARROW, Locale.KOREAN)


        val timePattern = DateTimeFormatter.ofPattern("HH:mm");
        val beforeTimeString = beforeDate.format(timePattern);
        view.showMessage("\n${monthDate} ${dayName}요일 ${beforeTimeString} -> $time 수정 완료!\n")

    }

    fun menu3() {
        println("메뉴3")
    }

    fun menu4() {
        println("메뉴4")
    }

}