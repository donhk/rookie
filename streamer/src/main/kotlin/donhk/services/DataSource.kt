package donhk.services

import org.ektorp.http.StdHttpClient
import org.ektorp.impl.StdCouchDbInstance
import org.ektorp.impl.StdCouchDbConnector


class DataSource constructor(private val url: String, private val dbName: String) {

    fun connect2Couch(): StdCouchDbConnector {
        val httpClient = StdHttpClient.Builder().url(url).build()
        val dbInstance = StdCouchDbInstance(httpClient)
        val connection = StdCouchDbConnector(dbName, dbInstance)
        //https://stackoverflow.com/questions/44595529/smart-cast-to-type-is-impossible-because-variable-is-a-mutable-property-tha
        connection.createDatabaseIfNotExists()
        return connection
    }

}