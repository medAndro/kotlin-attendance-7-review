package attendance

import attendance.controller.Controller

fun main() {
    val attendance = Controller.create()
    attendance.start()
}
