package attendance.model

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

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

    fun isAttendedNow(now:LocalDateTime):Boolean{
        val pattern = DateTimeFormatter.ofPattern("MM월 dd일");
        val nowMMDD = now.format(pattern);
        attendances.forEach{
            if (it.format(pattern) == nowMMDD){
                return true
            }
        }
        return false
    }

}