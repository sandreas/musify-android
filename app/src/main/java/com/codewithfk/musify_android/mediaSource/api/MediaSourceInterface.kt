package com.codewithfk.musify_android.mediaSource.api

import com.codewithfk.musify_android.mediaSource.api.model.MediaSourceItem

interface MediaSourceInterface {
    val id : String
    val name : String
    var active: Boolean

    suspend fun configure(config: MediaSourceConfiguration): Boolean
    suspend fun query(query: MediaSourceQueryInterface) : List<MediaSourceItem>
    suspend fun getItemById(id: String) : MediaSourceItem?
    suspend fun performAction(item: MediaSourceItem, action: MediaSourceAction, context:Any?) : MediaSourceItem
}