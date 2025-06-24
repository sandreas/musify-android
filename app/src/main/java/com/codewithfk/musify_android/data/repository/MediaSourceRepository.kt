package com.codewithfk.musify_android.data.repository

import android.media.session.MediaSession
import com.codewithfk.musify_android.mediaSource.api.MediaSourceInterface
import org.koin.core.annotation.Single

@Single
class MediaSourceRepository(private val mediaSession: MediaSession) {


    fun activateMediaSource(mediaSource: MediaSourceInterface) {
        return activateMediaSource(mediaSource.id)
    }
    fun activateMediaSource(id: String) {

    }

    fun insertMediaSource(mediaSource: MediaSourceInterface) {

    }

    fun deleteMediaSource(mediaSource: MediaSourceInterface) {
        deleteMediaSource(mediaSource.id)
    }

    fun deleteMediaSource(id: String) {

    }

    fun updateMediaSource(mediaSource: MediaSourceInterface) {
        updateMediaSource(mediaSource, mediaSource.id)
    }
    fun updateMediaSource(mediaSource: MediaSourceInterface, oldId: String) {

    }

}