package attendance.domain

import attendance.resources.AppConfig
import java.io.File
import java.time.LocalDateTime

class LoadService {

    fun loadAttendance(filePath: String): List<Pair<String, LocalDateTime>> =
        readCsvLines(filePath) { line ->
            createListFromFileLine(line)
        }

    private fun createListFromFileLine(fileLine: String): Pair<String, LocalDateTime> {
        val (name, date) = fileLine.split(",")
        return Pair(name, LocalDateTime.parse(date))
    }
    private fun <T> readCsvLines(filePath: String, mapper: (String) -> T): List<T> =
        File(filePath).readLines().drop(1).map(mapper)
}