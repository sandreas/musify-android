package com.codewithfk.musify_android.ui.feature.home

sealed class HomeEvent {
    data class showErrorMessage(val message: String) : HomeEvent()
}