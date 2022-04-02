package fr.devrtech.lbctestapp.core.net

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import fr.devrtech.lbctestapp.BuildConfig
import fr.devrtech.lbctestapp.core.entity.Album
import kotlinx.coroutines.delay
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * API Client
 */
class LeBonCoinAPIClient {


    // Rest API client
    private var _restClient: LeBonCoinAPIService

    // Gson
    private var gson: Gson = GsonBuilder().create()


    /**
     * Constructor
     */
    init {
        // Create
        // OkHttp builder for certificates
        val builder = OkHttpClient.Builder()
        // Socket timeout and read timeout (30s for long calculation requests)
        builder.connectTimeout(10, TimeUnit.SECONDS)
        builder.readTimeout(30, TimeUnit.SECONDS)
        if (BuildConfig.DEBUG) {
            // Logging Interceptor
            val interceptor = HttpLoggingInterceptor()
            //interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(interceptor)
        }
        // Create client
        val client = builder.build()
        // Retrofit client
        val retrofit = Retrofit.Builder()
            .baseUrl(LeBonCoinAPIService.API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
        _restClient = retrofit.create(LeBonCoinAPIService::class.java)
    }

    /**
     * Gell all albums from the web service
     */
    suspend fun getAllAlbum(): Response<List<Album>> {
        delay(10000)
        return _restClient.getAllAlbums()
    }

}
