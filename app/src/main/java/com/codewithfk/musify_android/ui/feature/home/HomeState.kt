package com.codewithfk.musify_android.ui.feature.home

sealed class HomeState {
    object Loading : HomeState()
    data class Success(val data: List<String>) : HomeState()
    data class Error(val message: String) : HomeState()
}