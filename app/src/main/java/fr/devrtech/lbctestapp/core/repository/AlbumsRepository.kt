package fr.devrtech.lbctestapp.core.repository

import fr.devrtech.lbctestapp.core.entity.Album

/**
 * Interface for albums repositories
 */
interface AlbumsRepository {


    /**
     * Get all albums
     *
     * @param cached Flag for retrieving albums from cahce, database or what else
     */
    suspend fun getAllAlbums(cached: Boolean): List<Album>?

}
