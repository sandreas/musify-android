package com.codewithfk.musify_android.data.model

data class HomeDataResponse(
    val continueListening: List<Song>,
    val recommendedSongs: List<Song>,
    val topMixes: List<Album>
)