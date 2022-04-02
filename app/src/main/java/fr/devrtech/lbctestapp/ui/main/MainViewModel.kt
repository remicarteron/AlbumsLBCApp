package fr.devrtech.lbctestapp.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fr.devrtech.lbctestapp.LBCTestApplication
import fr.devrtech.lbctestapp.R
import fr.devrtech.lbctestapp.core.entity.Album
import kotlinx.coroutines.*

/**
 * ViewModel
 */
class MainViewModel : ViewModel() {


    // Error message
    val errorMessage = MutableLiveData<String>()

    // Loading status
    val loading = MutableLiveData<Boolean>()

    // Current albums
    var currentAlbums = MutableLiveData<List<Album>>()

    // Current job
    var job: Job? = null


    /**
     * Load data
     */
    fun loadData(cached: Boolean) {
        // Check if job already running
        if (job == null || !job!!.isActive) {
            loading.value = true
            job = CoroutineScope(Dispatchers.IO).launch {
                // Get albums
                val response = LBCTestApplication.getRepository().getAllAlbums(cached)
                withContext(Dispatchers.Main) {
                    if (response != null) {
                        currentAlbums.value = response
                    } else {
                        onError(LBCTestApplication.getInstance().getString(R.string.error_loading))
                    }
                    loading.value = false
                }
            }
        } else if (job!!.isActive) {
            errorMessage.value =
                LBCTestApplication.getInstance().getString(R.string.already_loading)
        } else {
            onError(LBCTestApplication.getInstance().getString(R.string.error_loading))
        }
    }

    private fun onError(message: String) {
        errorMessage.value = message
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}
