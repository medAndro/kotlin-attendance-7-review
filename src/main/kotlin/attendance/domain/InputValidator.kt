package attendance.domain

import attendance.resources.Messages.*

class InputValidator {
    fun validateSelect(input: String, dateText:String): String {
        require(input.isNotBlank()) { WRONG_SYNTAX.errorMessage() }
        if (input != "Q"){
            require(input in listOf("1", "2", "3", "4")) { WRONG_SYNTAX.errorMessage() }
        } else {
            require(input == "Q") { WRONG_SYNTAX.errorMessage() }
        }
        return input
    }
}