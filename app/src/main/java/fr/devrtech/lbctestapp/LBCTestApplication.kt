package fr.devrtech.lbctestapp

import android.app.Application
import android.util.Log
import fr.devrtech.lbctestapp.core.repository.AlbumsRepoClient
import fr.devrtech.lbctestapp.core.repository.AlbumsRepository

/**
 * Application
 */
class LBCTestApplication : Application() {


    companion object {

        // TAGs
        private val TAG = LBCTestApplication::class.java.getSimpleName()

        // Singleton
        lateinit var APP_INSTANCE: LBCTestApplication

        /**
         * Get instance for Context
         */
        fun getInstance(): LBCTestApplication {
            return APP_INSTANCE
        }

        /**
         * Get repository instance
         */
        fun getRepository(): AlbumsRepository {
            return APP_INSTANCE.repository
        }

    }


    // Single instance of repository
    private lateinit var repository: AlbumsRepository


    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate !!!!!!")
        APP_INSTANCE = this
        repository = AlbumsRepoClient()
    }

}
