package fr.devrtech.lbctestapp.core.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import fr.devrtech.lbctestapp.core.entity.Album

/**
 * Room DAO for Albums
 */
@Dao
interface AlbumsRoomDAO {


    @Query("SELECT * FROM Album ORDER BY albumId ASC")
    fun getAllAlbums(): List<Album>

    @Insert
    fun insertAll(album: List<Album>)

    @Query("DELETE FROM Album")
    fun deleteAll()

}
