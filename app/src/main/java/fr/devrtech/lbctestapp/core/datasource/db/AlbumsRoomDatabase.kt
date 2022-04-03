package fr.devrtech.lbctestapp.core.datasource.db

import androidx.room.Database
import androidx.room.RoomDatabase
import fr.devrtech.lbctestapp.core.entity.Album

/**
 * Room database for Albums
 */
@Database(entities = [Album::class], version = 1)
abstract class AlbumsRoomDatabase : RoomDatabase() {

    abstract fun albumDao(): AlbumsRoomDAO

}
