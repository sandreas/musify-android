package com.codewithfk.musify_android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codewithfk.musify_android.data.MusifySession
import com.codewithfk.musify_android.data.repository.StatusRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class MainViewModel(val repository: StatusRepository, val musifySession: MusifySession) :
    ViewModel() {

    private val state = MutableStateFlow("")
    // val status = state.asStateFlow()

    init {
        restoreLastSession()
    }

    fun isUserLoggedIn(): Boolean {
        return musifySession.getToken() != null
    }

    private fun restoreLastSession() {
        viewModelScope.launch {

            // todo
            // - integrate "addMediaSourceConfig" and "updateMediaSourceConfig"
            // - change "getActiveMediaSource" to getActiveMediaSourceConfig
            // - add "
            val mediaSource = musifySession.getActiveMediaSource()
            if (mediaSource != null) {

            }
            val result = repository.getStatus()
            state.value = result
        }
    }
}