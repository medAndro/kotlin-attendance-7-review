package attendance.model

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.*

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

    fun getHistory(now: LocalDateTime){
        attendances.sort()
        val monthDatePattern = DateTimeFormatter.ofPattern("MM월 dd일")
        val hourMinutePattern = DateTimeFormatter.ofPattern("HH:mm")
        val todayDate = now.format(DateTimeFormatter.ofPattern("dd")).toInt()

        for(i in 1..todayDate){
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            val fordate = LocalDateTime.parse("2024-12-${"%02d".format(i)} 01:01", formatter)
            val dayName = fordate.dayOfWeek.getDisplayName(TextStyle.NARROW, Locale.KOREAN)
            if (dayName in listOf("월","화","수","목","금")){
                val found = attendances.find { it.format(monthDatePattern) == fordate.format(monthDatePattern) }

                if (found == null){
                    val monthDate = fordate.format(monthDatePattern)
                    val dayName = fordate.dayOfWeek.getDisplayName(TextStyle.NARROW, Locale.KOREAN)
                    println("${monthDate} ${dayName}요일 --:--")

                }else{
                    val monthDate = found.format(monthDatePattern)
                    val hourMinute = found.format(hourMinutePattern)
                    val dayName = found.dayOfWeek.getDisplayName(TextStyle.NARROW, Locale.KOREAN)
                    println("${monthDate} ${dayName}요일 ${hourMinute}")
                }

            }

        }
//        attendances.forEach{
//            val monthDate = it.format(monthDatePattern)
//            val hourMinute = it.format(hourMinutePattern)
//            val dayName = it.dayOfWeek.getDisplayName(TextStyle.NARROW, Locale.KOREAN)
//            println("${monthDate} ${dayName}요일 ${hourMinute}")
//        }
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