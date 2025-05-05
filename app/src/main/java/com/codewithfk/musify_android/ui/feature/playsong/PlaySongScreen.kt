package com.codewithfk.musify_android.ui.feature.playsong

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.codewithfk.musify_android.ui.feature.widgets.ErrorScreen
import com.codewithfk.musify_android.ui.feature.widgets.LoadingScreen
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun PlaySongScreen(songID:String, navController: NavController, viewModel: PlaySongViewModel = koinViewModel()) {

    LaunchedEffect(true) {
        viewModel.fetchData(songID)
        viewModel.event.collectLatest {
            when (it) {
                is PlaySongEvent.showErrorMessage -> {
                    Toast.makeText(navController.context, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    val state = viewModel.state.collectAsStateWithLifecycle()
    when (state.value) {
        is PlaySongState.Loading -> {
            LoadingScreen()
        }

        is PlaySongState.Success -> {
        }

        is PlaySongState.Error -> {
            val errorMessage = (state.value as PlaySongState.Error).message
            ErrorScreen(errorMessage, "Retry", onPrimaryButtonClicked = {})
        }
    }
}