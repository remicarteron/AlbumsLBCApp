package fr.devrtech.lbctestapp.core.repository

import androidx.room.Room
import fr.devrtech.lbctestapp.LBCTestApplication
import fr.devrtech.lbctestapp.core.datasource.db.AlbumsRoomDatabase
import fr.devrtech.lbctestapp.core.entity.Album
import fr.devrtech.lbctestapp.core.datasource.net.LeBonCoinAPIClient

/**
 * Repository client
 */
class AlbumsRepoClient(val webServiceClient : LeBonCoinAPIClient, val db: AlbumsRoomDatabase) : AlbumsRepository {


    companion object {

        // TAGs
        private val TAG = LBCTestApplication::class.java.getSimpleName()

    }


    override suspend fun getAllAlbums(cached: Boolean): List<Album>? {
        if (cached) {
            db.albumDao().getAllAlbums().let {
                return it
            }
        } else {
            // Delete data from db
            db.albumDao().deleteAll()
        }
        // Get data from web service
        val response = webServiceClient.getAllAlbum()
        if (response.isSuccessful) {
            // Data
            val body = response.body()
            body?.let { albumsList ->
                // Insert data into db
                db.albumDao().insertAll(albumsList)
                return albumsList
            }
        }
        // No data available
        return null
    }

}
