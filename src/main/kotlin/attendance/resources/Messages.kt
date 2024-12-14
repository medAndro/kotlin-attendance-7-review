package attendance.resources

enum class Messages(private val message: String) {
    INFO("[INFO] %s"),
    SUM_START_HEADER("덧셈을 시작합니다"),
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

    ERROR("[ERROR] %s"),
    EMPTY_INPUT("입력값이 비어있습니다."),
    NOT_INTEGER("입력값이 정수가 아닙니다."),
    INVALID_ERROR("알 수 없는 오류가 발생했습니다.");

    fun message(): String = message
    fun infoMessage(): String = INFO.formattedMessage(message)
    fun errorMessage(): String = ERROR.formattedMessage(message)
    fun formattedMessage(vararg args: Any): String = String.format(message, *args)
}
