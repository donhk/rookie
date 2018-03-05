package donhk.hiking

import com.mpatric.mp3agic.Mp3File
import java.io.File

class PathScanner constructor(private val path: String) {

    fun scan(): List<String> {
        println("Scanning files")
        val files = mutableListOf<File>()
        File(path).walkTopDown().filter(File::isFile).filter { file -> file.name.endsWith(".mp3", true) }.toCollection(files)
        println("finished ${files.size}")
        files.forEach { song ->
            processSong(song)
        }
        return listOfNotNull<String>(null)
    }

    private fun processSong(song: File) {
        val mp3File = Mp3File(song.absolutePath)
        println("${mp3File.filename} bitrate ${mp3File.bitrate} album ${mp3File.id3v2Tag}")

    }

}