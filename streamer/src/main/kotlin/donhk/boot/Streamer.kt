package donhk.boot

import donhk.hiking.PathScanner
import donhk.services.DataSource
import donhk.services.TracksRepository
import java.util.*

class Streamer {
    companion object {
        private val data = ResourceBundle.getBundle("data")
        private val hostname: String = data.getString("db.hostname")
        private val port: String = data.getString("db.port")
        private val database: String = data.getString("db.name")
        @JvmStatic
        fun main(args: Array<String>) {
            val scanner = PathScanner("C:\\media")
            val trackList = scanner.scan()
            val dataSource = DataSource("http://$hostname:$port", database)
            val tracksRepository = TracksRepository(dataSource.connect2Couch())
            trackList.forEach { t ->
                if (!tracksRepository.contains(t.id)) {
                    tracksRepository.add(t)
                }
            }

            val dbTracks = tracksRepository.all
            println("--------------------------------------------")
            println("total tracks ${dbTracks.size}")
            println("--------------------------------------------")
        }
    }
}