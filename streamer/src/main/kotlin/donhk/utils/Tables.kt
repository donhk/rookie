package donhk.utils

import org.jetbrains.exposed.sql.*

object Requests : Table() {
    val id = (integer("id") references Tracks.id) // Column<Int>
    val hits = integer("hits") // Column<Int>
    val status = varchar("status", 10).default("NEW")
}

object Tracks : Table() {
    val id = integer("id").autoIncrement().uniqueIndex()
    val title = varchar("title", 100) // Column<String>
    val location = varchar("location", 2000)
    val hash = varchar("hash", 50).uniqueIndex()
    val genre = varchar("genre", 100)
    val length = long("length")
    val album = varchar("album", 100)
    val year = varchar("year", 5)
    val trackNo = varchar("trackNo", 100)
    val bitrate = integer("bitrate")
    val size = long("size")
}
