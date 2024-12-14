package attendance.model

import java.time.LocalDateTime


class AttendanceBook {
    private val members: MutableList<Member> = mutableListOf()
    private val names: MutableList<String> = mutableListOf()
    fun addByPair(pairs: List<Pair<String, LocalDateTime>>){
        pairs.forEach {
            if (it.first !in names){
                names.add(it.first)
                members.add(Member(it.first, mutableListOf()))
            }
            for (member in members) {
                if (member.getName() == it.first){
                    member.addAttendances(it.second)
                }
            }
        }
    }
    fun getNames():List<String>{
        return names.toList()
    }
    fun getMembers():List<Member>{
        return members.toList()
    }

    fun isRightNickName(nickName:String): Boolean{
        if (nickName in names){
            return true
        }
        return false
    }

    fun isNameInBook(nickName:String, now:LocalDateTime): Boolean{
        val member = getMemberByName(nickName)
        return member.isAttendedNow(now)
    }

    fun addAttendanceDate(nickName:String, date:LocalDateTime){
        val member = getMemberByName(nickName)
        member.addDate(date)
    }

    fun editAttendanceDate(nickName:String, date:LocalDateTime):LocalDateTime{
        val member = getMemberByName(nickName)
        return member.editDate(date)
    }

    fun getAttendanceDateHistory(now: LocalDateTime, nickName:String ){
        val member = getMemberByName(nickName)

        member.getHistory(now)
    }

    private fun getMemberByName(nickName:String):Member{
        for (member in members) {
            if (member.getName() == nickName){
                return member
            }
        }
        return Member("멤버 없음", mutableListOf())
    }
}