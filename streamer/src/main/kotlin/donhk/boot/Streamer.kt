package donhk.boot

import donhk.hiking.PathScanner
import java.util.*

class Streamer {
    companion object {
        val data = ResourceBundle.getBundle("data")
        @JvmStatic
        fun main(args: Array<String>) {
            println("Hello world this is a test")
            val scanner = PathScanner("C:\\media")
            println("scan")
            scanner.scan()
            val hostname = data.getString("db.hostname")
            print("hostname $hostname")
        }
    }
}