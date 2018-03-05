package donhk.boot

import donhk.hiking.PathScanner
import donhk.utils.MessageBundle

class Streamer {
    companion object {
        val data = MessageBundle("resources/main/data.properties")
        @JvmStatic
        fun main(args: Array<String>) {
            println("Hello world this is a test")
            val scanner = PathScanner("C:\\media")
            println("scan")
            scanner.scan()
            val hostname = data.get("db.hostname")
            print("hostname $hostname")
        }
    }
}