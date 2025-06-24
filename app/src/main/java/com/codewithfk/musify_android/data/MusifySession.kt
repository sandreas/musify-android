package com.codewithfk.musify_android.data

import android.content.Context
import android.util.Log
import org.koin.core.annotation.Single
import androidx.core.content.edit
import com.codewithfk.musify_android.mediaSource.api.MediaSourceConfiguration
import com.codewithfk.musify_android.mediaSource.api.MediaSourceInterface
import com.codewithfk.musify_android.mediaSource.implementation.audiobookshelf.AudioBookShelfMediaSource
import com.codewithfk.musify_android.mediaSource.implementation.mock.MockMediaSource
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Single
class MusifySession(private val context: Context) {
    companion object {
        val KEY_ACTIVE_MEDIA_SOURCE_ID = "activeMediaSourceId"
    }

    // The difference is that Dispatchers.Default is limited to the number of CPU cores (with a minimum of 2) so only N (where N == cpu cores) tasks can run in parallel in this dispatcher.
    // On the IO dispatcher there are by default 64 threads, so there could be up to 64 parallel tasks running on that dispatcher.
    // The idea is that the IO dispatcher spends a lot of time waiting (IO blocked), while the Default dispatcher is intended for CPU intensive tasks, where there is little or no sleep.
    // private val sessionScope = CoroutineScope(Dispatchers.Default)

    private val gson = Gson()
    private val mediaSources = ArrayList<MediaSourceInterface>()



    private val sharedPreferences =
        context.getSharedPreferences("musify_session", Context.MODE_PRIVATE)

    init {
        /*
        sessionScope.launch {
            loadMediaSources()
        }

         */
    }

    fun loadMediaSourceConfigurations(): List<MediaSourceConfiguration>? {
        var mediaSourcesJson = sharedPreferences.getString("mediaSources", null)
        if(mediaSourcesJson == null) {
            return null
        }

        try {
            return gson.fromJson<ArrayList<MediaSourceConfiguration>>(mediaSourcesJson,
                ArrayList::class.java)
        } catch (e: Exception) {
            Log.d(this::class.simpleName, "Could not decode mediaSources", e)
        }
        return null
    }

    fun saveMediaSourceConfigurations(mediaSources : List<MediaSourceConfiguration>?)  {
        val toSave = gson.toJson(mediaSources)
        sharedPreferences.edit {
            putString("mediaSourceConfigurations", toSave)
        }
    }




    fun saveToken(token: String) {
        sharedPreferences.edit {
            putString("token", token)
        }
    }

    fun getToken(): String? {
        return sharedPreferences.getString("token", null)
    }

    fun saveUserName(userName: String) {
        sharedPreferences.edit {
            putString("user_name", userName)
        }
    }

    fun getUserName(): String? {
        return sharedPreferences.getString("user_name", null)
    }

    fun clearSession() {
        sharedPreferences.edit {
            clear()
        }
    }


    fun setActiveMediaSource(id: String) {
        sharedPreferences.edit {
            putString(KEY_ACTIVE_MEDIA_SOURCE_ID, id)
        }
    }

    fun getActiveMediaSource(): MediaSourceInterface? {
        val activeId = sharedPreferences.getString(KEY_ACTIVE_MEDIA_SOURCE_ID, "")
        val activeMediaSource = mediaSources.find { it -> activeId == it.id}
        if(activeMediaSource != null) {
            return activeMediaSource
        }

        return mediaSources.firstOrNull()
    }
}