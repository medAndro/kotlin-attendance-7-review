package attendance.view

import camp.nextstep.edu.missionutils.Console

class GameView {
    // Input Views
    fun readLine(): String {
        return Console.readLine()
    }

    // Output Views
    fun showBlankLine() {
        println()
    }

    fun showMessage(message: String) {
        println(message)
    }
}