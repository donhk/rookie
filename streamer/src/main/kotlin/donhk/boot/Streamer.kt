package donhk.boot

import donhk.hiking.PathScanner

class Streamer {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println("Hello world this is a test")
            val scanner = PathScanner("C:\\media")
            println("scan")
            scanner.scan()
        }
    }
}