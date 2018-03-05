package donhk.utils

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class Track constructor() {
    public var _id: String = ""
    public var title: String = ""
    public var location: String = ""
    public var genre: String = ""
    public var length: Long = 0
    public var album: String = ""
    public var year: Int = 0
    public var trackNo: Int = 0
    public var bitrate: String = ""
    public var size: Double = 0.0
}