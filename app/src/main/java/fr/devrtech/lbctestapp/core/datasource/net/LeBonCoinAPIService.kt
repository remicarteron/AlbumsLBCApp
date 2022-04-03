package fr.devrtech.lbctestapp.core.datasource.net

import fr.devrtech.lbctestapp.core.entity.Album
import retrofit2.Response
import retrofit2.http.GET

/**
 * API for Le Bon Coin API Service
 */
interface LeBonCoinAPIService {

    companion object {

        // Base URL
        const val API_BASE_URL = "https://static.leboncoin.fr/img/shared/"

    }

    @GET("technical-test.json")
    suspend fun getAllAlbums(): Response<List<Album>>

}
