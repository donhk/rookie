//https://github.com/JetBrains/Exposed
//https://github.com/JetBrains/Exposed/wiki/DAO#basic-crud-operations
package donhk.boot

import donhk.hiking.PathScanner
import donhk.utils.Requests
import donhk.utils.Tracks
import java.util.*

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.SchemaUtils.create
import org.jetbrains.exposed.sql.SchemaUtils.drop

class Streamer {
    companion object {

        private val data = ResourceBundle.getBundle("data")
        private val hostname: String = data.getString("db.hostname")
        private val port: String = data.getString("db.port")
        private val database: String = data.getString("db.name")
        private val user: String = data.getString("db.user")
        private val pass: String = data.getString("db.password")

        @JvmStatic
        fun main(args: Array<String>) {
            Database.connect("jdbc:h2:tcp://$hostname:$port/$database", driver = "org.h2.Driver", user = user, password = pass)
            transaction {
                //drop(Tracks, Requests)
                if (!Tracks.exists()) {
                    create(Tracks)
                }
                if (!Requests.exists()) {
                    create(Requests)
                }
            }
            val scanner = PathScanner("C:\\media")
            val trackList = scanner.scan()
            transaction {
                trackList.forEach { track ->
                    val e = Tracks.select { Tracks.hash.eq(track.hash) }.count()
                    if (e == 0) {
                        println("insert ${track.title}")
                        Tracks.insert {
                            it[title] = track.title
                            it[location] = track.location
                            it[hash] = track.hash
                            it[genre] = track.genre
                            it[length] = track.length
                            it[album] = track.album
                            it[year] = track.year
                            it[trackNo] = track.trackNo
                            it[bitrate] = track.bitrate
                            it[size] = track.size
                        }
                    } else {
                        println("skip ${track.title}")
                    }
                }
            }
        }
    }
}