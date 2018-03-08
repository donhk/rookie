package donhk.boot

import donhk.hiking.PathScanner
import donhk.services.DataSource
import donhk.services.TracksRepository
import java.util.*

class Streamer {
    companion object {
        private val data = ResourceBundle.getBundle("data")
        val hostname = data.getString("db.hostname")
        val port = data.getString("db.port")
        val database = data.getString("db.name")
        @JvmStatic
        fun main(args: Array<String>) {
            val scanner = PathScanner("C:\\media")
            val trackList = scanner.scan()
            val dataSource = DataSource("http://$hostname:$port", database)
            val tracksRepository = TracksRepository(dataSource.connect2Couch())

            trackList.forEach { t ->
                tracksRepository.add(t)
            }

            val dbTracks = tracksRepository.all
            print("test ${dbTracks[0].title}")
        }
    }
}