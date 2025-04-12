package com.codewithfk.musify_android.ui.feature.login

sealed class LoginState {
    object Nothing : LoginState()
    object Loading : LoginState()
    data class Success(val data: List<String>) : LoginState()
    data class Error(val message: String) : LoginState()
}