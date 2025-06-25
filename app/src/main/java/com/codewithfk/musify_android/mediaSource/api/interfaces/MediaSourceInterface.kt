package com.codewithfk.musify_android.mediaSource.api.interfaces

import com.codewithfk.musify_android.mediaSource.api.MediaSourceAction
import com.codewithfk.musify_android.mediaSource.api.MediaSourceConfiguration
import com.codewithfk.musify_android.mediaSource.api.interfaces.MediaSourceQueryInterface
import com.codewithfk.musify_android.mediaSource.api.model.MediaSourceItem

interface MediaSourceInterface {
    val id : String
    val name : String
    var active: Boolean

    fun configure(config: MediaSourceConfiguration): MediaSourceInterface?
    suspend fun query(query: MediaSourceQueryInterface) : List<MediaSourceItem>
    suspend fun getItemById(id: String) : MediaSourceItem?
    suspend fun performAction(item: MediaSourceItem, action: MediaSourceAction, context:Any?) : MediaSourceItem
}