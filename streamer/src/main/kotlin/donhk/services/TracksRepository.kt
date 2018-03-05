package donhk.services

import donhk.utils.Track
import org.ektorp.CouchDbConnector
import org.ektorp.support.CouchDbRepositorySupport

class TracksRepository constructor(connection: CouchDbConnector) : CouchDbRepositorySupport<Track>(Track::class.java, connection) {
    //https://github.com/helun/Ektorp
}