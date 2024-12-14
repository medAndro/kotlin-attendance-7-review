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

    fun addDate(date:LocalDateTime){
        attendances.add(date)
    }

    fun editDate(date:LocalDateTime):LocalDateTime{
        attendances.forEach{
            val pattern = DateTimeFormatter.ofPattern("MM월 dd일");
            val itMMDD = it.format(pattern);
            val nowMMDD = date.format(pattern);
            if(itMMDD == nowMMDD){
                val beforeDate = it
                attendances.remove(it)
                attendances.add(date)
                return beforeDate
            }
        }
        return date
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