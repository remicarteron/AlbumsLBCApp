package fr.devrtech.lbctestapp.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.snackbar.Snackbar
import fr.devrtech.lbctestapp.R
import fr.devrtech.lbctestapp.core.entity.Album
import kotlinx.android.synthetic.main.main_fragment.*

/**
 * Main fragment
 */
class MainFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {


    // TAG
    private val TAG = MainFragment::class.java.getSimpleName()


    companion object {
        fun newInstance() = MainFragment()
    }


    // Lock for loading only once a time
    private var loadingLock = false

    // View model
    private lateinit var viewModel: MainViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // Init UI
        initUI()

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        // Init all observers
        initObservers(viewModel)
    }

    override fun onResume() {
        super.onResume()
        // Load data
        viewModel.loadData(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.getItemId()
        Log.d(TAG, "onOptionsItemSelected !!!!!!" + id)
        when (id) {
            R.id.action_refresh -> {
                onRefresh()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onRefresh() {
        // Refresh data
        viewModel.loadData(false)
    }

    private fun initUI() {
        // Enable menu items
        setHasOptionsMenu(true)
        // Swipe listener
        main_swipe.setOnRefreshListener(this)
        // Init recycler layout
        main_recycler.layoutManager =
            GridLayoutManager(context, resources.getInteger(R.integer.thumb_columns))
    }

    private fun initObservers(viewModel: MainViewModel) {
        // Create the observers which updates the UI.
        val albumsObserver = Observer<List<Album>> { albums ->
            Log.d(TAG, "Albums : " + albums.size)
            // Update the UI, in this case, the recycler
            main_recycler.visibility = View.VISIBLE
            main_recycler.adapter = AlbumsListAdapter(albums)
            main_text_message.visibility = View.GONE
        }
        val messageObserver = Observer<String> { message ->
            // Update the UI, in this case, the TextView.
            main_recycler.visibility = View.GONE
            main_text_message.visibility = View.VISIBLE
            main_text_message.text = message
            checkUI()
        }
        val loadingObserver = Observer<Boolean> { loadingStatus ->
            // Update the UI, in this case, the refresh.
            main_swipe.isRefreshing = loadingStatus
            if (loadingStatus) {
                checkUI()
            }
            loadingLock = loadingStatus
        }
        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        viewModel.currentAlbums.observe(this.requireActivity(), albumsObserver)
        viewModel.errorMessage.observe(this.requireActivity(), messageObserver)
        viewModel.loading.observe(this.requireActivity(), loadingObserver)
    }

    private fun checkUI() {
        if (main_swipe.isRefreshing) {
            if (loadingLock) {
                Snackbar.make(main_layout_main, R.string.already_loading, Snackbar.LENGTH_SHORT)
                    .show()
            } else {
                // Loading message
                main_text_message.text = getString(R.string.loading)
                main_recycler.visibility = View.GONE
                main_text_message.visibility = View.VISIBLE
                // Show snack
                Snackbar.make(main_layout_main, R.string.loading, Snackbar.LENGTH_SHORT).show()
            }
        }
    }

}
