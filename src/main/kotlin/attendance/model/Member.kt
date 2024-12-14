package attendance.model

import java.time.LocalDateTime

class Member(
    private val name: String,
    private val attendances: MutableList<LocalDateTime>
) {
    fun getName():String{
        return name
    }
    fun getAttendances():List<LocalDateTime>{
        return attendances.toList()
    }
    fun addAttendances(time: LocalDateTime){
        attendances.add(time)
    }

}