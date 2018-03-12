package donhk.hiking

import com.mpatric.mp3agic.Mp3File
import donhk.utils.HashUtils
import donhk.utils.Track

import java.io.File

class PathScanner constructor(private val path: String) {
    var pending: Int = -1
    fun scan(): List<Track> {
        val files = mutableListOf<File>()
        val tracks = mutableListOf<Track>()
        File(path).walkTopDown().filter(File::isFile).filter { file -> file.name.endsWith(".mp3", true) }.toCollection(files)
        pending = files.size
        println("total files to process ${files.size}")
        files.forEach { song ->
            val mp3File = Mp3File(song.absolutePath)
            val track = Track()
            track.hash = HashUtils.sha1(song.absolutePath).substring(0, 12).toLowerCase()
            when {
                mp3File.hasId3v2Tag() -> {
                    val meta = mp3File.id3v2Tag
                    meta.title?.let { t -> track.title = t }
                    track.genre = meta.genreDescription
                    track.length = mp3File.lengthInSeconds
                    track.album = meta.album
                    track.year = meta.year
                    track.trackNo = meta.track
                    track.bitrate = mp3File.bitrate
                    track.size = mp3File.length
                    track.location = song.absolutePath
                }
                mp3File.hasId3v1Tag() -> {
                    val meta = mp3File.id3v1Tag
                    meta.title?.let { t -> track.title = t }
                    track.genre = meta.genreDescription
                    track.length = mp3File.lengthInSeconds
                    track.album = meta.album
                    track.year = meta.year
                    meta.track?.let { t -> track.trackNo = t }
                    track.bitrate = mp3File.bitrate
                    track.size = mp3File.length
                    track.location = song.absolutePath
                }
                else -> {
                    track.title = song.name
                    track.genre = ""
                    track.length = mp3File.lengthInSeconds
                    track.album = ""
                    track.year = ""
                    track.trackNo = ""
                    track.bitrate = mp3File.bitrate
                    track.size = mp3File.length
                    track.location = song.absolutePath
                }
            }
            tracks.add(track)
            pending--
        }
        return tracks
    }

}