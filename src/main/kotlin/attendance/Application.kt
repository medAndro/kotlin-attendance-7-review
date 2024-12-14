package attendance

import attendance.controller.GameController

fun main() {
    val game = GameController.create()
    game.gameStart()
}
