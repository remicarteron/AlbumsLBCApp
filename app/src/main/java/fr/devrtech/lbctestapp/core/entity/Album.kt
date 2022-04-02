package fr.devrtech.lbctestapp.core.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

/**
 * Album entity
 */
@Entity
class Album() : Serializable {

    constructor(
        id: Long?,
        albumId: Long?,
        title: String?,
        url: String?,
        thumbnailUrl: String?
    ) : this() {
        this.id = id
        this.albumId = albumId
        this.title = title
        this.url = url
        this.thumbnailUrl = thumbnailUrl
    }

    @PrimaryKey
    var id: Long? = null

    var albumId: Long? = null

    var title: String? = null

    var url: String? = null

    var thumbnailUrl: String? = null


}
