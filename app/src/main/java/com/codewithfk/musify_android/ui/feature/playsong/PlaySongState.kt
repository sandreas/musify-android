package com.codewithfk.musify_android.ui.feature.playsong

sealed class PlaySongState {
    object Loading : PlaySongState()
    data class Success(val data: List<String>) : PlaySongState()
    data class Error(val message: String) : PlaySongState()
}