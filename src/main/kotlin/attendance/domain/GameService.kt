package attendance.domain

import attendance.model.NumberBasket

class GameService {
    fun plusTwoNumber(numberBasket: NumberBasket): Int {
        return numberBasket.getNumbers().sum()
    }

    fun getExpression(numberBasket: NumberBasket): String {
        return numberBasket.getNumbers().joinToString(separator = " + ")
    }
}