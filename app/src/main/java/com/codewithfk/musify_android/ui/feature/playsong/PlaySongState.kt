package com.codewithfk.musify_android.ui.feature.playsong

import com.codewithfk.musify_android.data.model.Song

sealed class PlaySongState {
    object Loading : PlaySongState()
    data class Success(
        val isPlaying: Boolean = false,
        val currentSong: Song? = null,
        val currentPosition: Long = 0L,
        val duration: Long = 0L,
        val error: String? = null,
        val isBuffering: Boolean = false,
    ) : PlaySongState()

    data class Error(val message: String) : PlaySongState()
}