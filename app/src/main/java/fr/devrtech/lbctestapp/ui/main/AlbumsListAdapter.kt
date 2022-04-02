package fr.devrtech.lbctestapp.ui.main

import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.devrtech.lbctestapp.R
import fr.devrtech.lbctestapp.core.entity.Album
import kotlinx.android.synthetic.main.item_album.view.*

/**
 * Adapter for albums
 */
class AlbumsListAdapter(private val albumsList: List<Album>) :
    RecyclerView.Adapter<AlbumsListAdapter.AlbumViewHolder>() {


    // TAG
    private val TAG = AlbumsListAdapter::class.java.getSimpleName()


    /**
     * Create new views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        // create a new view
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_album, parent, false)
        return AlbumViewHolder(view)
    }

    /**
     * Replace the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        // Get element from your dataset at this position
        val item: Album = albumsList[position]
        // Fill view with content
        holder.titleTextView.text = item.title
        Log.d(TAG, "Loading thumb url : " + item.thumbnailUrl)
        if (!TextUtils.isEmpty(item.thumbnailUrl)) {
            // Image loading
            Picasso.get().load(item.thumbnailUrl).into(holder.thumbImageView)
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = albumsList.size


    /**
     * View holder
     */
    inner class AlbumViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val thumbImageView: ImageView = view.item_image_thumb

        val titleTextView: TextView = view.item_text_title

    }

}
