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
}