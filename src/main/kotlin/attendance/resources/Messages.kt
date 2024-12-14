package attendance.resources

enum class Messages(private val message: String) {
    INFO("[INFO] %s"),
    INPUT_NICKNAME("\n닉네임을 입력해 주세요."),
    INPUT_TIME("등교 시간을 입력해 주세요."),
    INPUT_EDIT_NICKNAME("\n출석을 수정하려는 크루의 닉네임을 입력해 주세요."),
    INPUT_EDIT_DATE("\n수정하려는 날짜(일)를 입력해 주세요."),
    INPUT_EDIT_TIME("\n언제로 변경하겠습니까?"),


    LEFT_VALUE_INPUT("좌변의 값을 입력하세요"),
    RIGHT_VALUE_INPUT("우변의 값을 입력하세요"),
    START_INFO(
        """
        오늘은 %s %s요일입니다. 기능을 선택해 주세요.
        1. 출석 확인
        2. 출석 수정
        3. 크루별 출석 기록 확인
        4. 제적 위험자 확인
        Q. 종료
        """.trimIndent()
    ),

    ERROR("[ERROR] %s\n"),
    WRONG_SYNTAX("잘못된 형식을 입력하였습니다.\n"),
    HOLIDAY("[ERROR] %s %s요일은 등교일이 아닙니다.\n"),
    WRONG_NICKNAME("등록되지 않은 닉네임입니다.\n"),
    WRONG_TIME("캠퍼스 운영 시간에만 출석이 가능합니다.\n"),
    CANT_EDIT("아직 수정할 수 없습니다.\n"),
    READY_ATTENDANCE("이미 출석을 확인하였습니다. 필요한 경우 수정 기능을 이용해 주세요."),

    EMPTY_INPUT("입력값이 비어있습니다."),
    NOT_INTEGER("입력값이 정수가 아닙니다."),
    INVALID_ERROR("알 수 없는 오류가 발생했습니다.");

    fun message(): String = message
    fun infoMessage(): String = INFO.formattedMessage(message)
    fun errorMessage(): String = ERROR.formattedMessage(message)
    fun formattedMessage(vararg args: Any): String = String.format(message, *args)
}
