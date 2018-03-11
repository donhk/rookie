package donhk.utils

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.ektorp.support.CouchDbDocument
import org.ektorp.support.TypeDiscriminator

//https://helun.github.io/Ektorp/tutorial.html
@JsonIgnoreProperties(ignoreUnknown = true)
class Track : CouchDbDocument() {
    var title: String = ""
    @TypeDiscriminator("location")
    var location: String = ""
    var genre: String = ""
    var length: Long = 0
    var album: String = ""
    var year: String = ""
    var trackNo: String = ""
    var bitrate: Int = 0
    var size: Long = 0
}