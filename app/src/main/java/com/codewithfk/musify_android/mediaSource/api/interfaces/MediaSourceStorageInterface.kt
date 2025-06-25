package com.codewithfk.musify_android.mediaSource.api.interfaces

import com.codewithfk.musify_android.mediaSource.api.MediaSourceConfiguration

interface MediaSourceStorageInterface {
    fun loadMediaSourceConfigurations(): List<MediaSourceConfiguration>?
    fun saveMediaSourceConfigurations(mediaSourceConfigurations : List<MediaSourceConfiguration>?)
}