package com.codewithfk.musify_android.mediaSource.implementation.navidrome

import com.codewithfk.musify_android.mediaSource.api.MediaSourceAction
import com.codewithfk.musify_android.mediaSource.api.MediaSourceConfiguration
import com.codewithfk.musify_android.mediaSource.api.interfaces.MediaSourceInterface
import com.codewithfk.musify_android.mediaSource.api.interfaces.MediaSourceQueryInterface
import com.codewithfk.musify_android.mediaSource.api.model.MediaSourceItem
import com.codewithfk.musify_android.mediaSource.implementation.AbstractMediaSource
import okhttp3.internal.immutableListOf

class NavidromeMediaSource(override val id: String, override val name: String) : AbstractMediaSource(id, name) {
    override fun configure(config: MediaSourceConfiguration): MediaSourceInterface? {
        return this
    }

    override suspend fun query(query: MediaSourceQueryInterface): List<MediaSourceItem> {
        return immutableListOf()
    }

    override suspend fun getItemById(id: String): MediaSourceItem? {
        return null
    }

    override suspend fun performAction(
        item: MediaSourceItem,
        action: MediaSourceAction,
        context: Any?
    ): MediaSourceItem {
        return item
    }
}