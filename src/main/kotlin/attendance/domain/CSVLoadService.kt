package attendance.domain

import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class CSVLoadService {

    fun loadAttendance(filePath: String): List<Pair<String, LocalDateTime>> =
        readCsvLines(filePath) { line ->
            createListFromFileLine(line)
        }

    private fun createListFromFileLine(fileLine: String): Pair<String, LocalDateTime> {
        val (name, date) = fileLine.split(",")
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return Pair(name, LocalDateTime.parse(date, formatter))
    }

    private fun <T> readCsvLines(filePath: String, mapper: (String) -> T): List<T> =
        File(filePath).readLines().drop(1).map(mapper)
}