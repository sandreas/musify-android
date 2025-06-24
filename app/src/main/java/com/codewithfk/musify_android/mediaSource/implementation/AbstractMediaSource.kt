package com.codewithfk.musify_android.mediaSource.implementation

import com.codewithfk.musify_android.mediaSource.api.MediaSourceInterface

abstract class AbstractMediaSource(override val id: String, override val name: String): MediaSourceInterface {
    override var active: Boolean = false
}