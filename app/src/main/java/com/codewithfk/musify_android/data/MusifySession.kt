package com.codewithfk.musify_android.data

import android.content.Context
import android.util.Log
import org.koin.core.annotation.Single
import androidx.core.content.edit
import com.codewithfk.musify_android.mediaSource.api.MediaSourceConfiguration
import com.codewithfk.musify_android.mediaSource.api.interfaces.MediaSourceInterface
import com.codewithfk.musify_android.mediaSource.api.interfaces.MediaSourceStorageInterface
import com.google.gson.Gson

@Single
class MusifySession(private val context: Context): MediaSourceStorageInterface {
    companion object {
        val KEY_SHARED_PREFERENCES = "musify_session"
        val KEY_MEDIA_SOURCE_CONFIGURATIONS = "mediaSourceConfigurations"
    }

    // The difference is that Dispatchers.Default is limited to the number of CPU cores (with a minimum of 2) so only N (where N == cpu cores) tasks can run in parallel in this dispatcher.
    // On the IO dispatcher there are by default 64 threads, so there could be up to 64 parallel tasks running on that dispatcher.
    // The idea is that the IO dispatcher spends a lot of time waiting (IO blocked), while the Default dispatcher is intended for CPU intensive tasks, where there is little or no sleep.
    // private val sessionScope = CoroutineScope(Dispatchers.Default)

    private val gson = Gson()
    private val mediaSources = ArrayList<MediaSourceInterface>()



    private val sharedPreferences =
        context.getSharedPreferences(KEY_SHARED_PREFERENCES, Context.MODE_PRIVATE)

    init {
        /*
        sessionScope.launch {
            loadMediaSources()
        }

         */
    }

    override fun loadMediaSourceConfigurations(): List<MediaSourceConfiguration>? {
        var mediaSourceConfigurationsJson = sharedPreferences.getString(KEY_MEDIA_SOURCE_CONFIGURATIONS, null)
        if(mediaSourceConfigurationsJson == null) {
            return null
        }

        try {
            return gson.fromJson<ArrayList<MediaSourceConfiguration>>(mediaSourceConfigurationsJson,
                ArrayList::class.java)
        } catch (e: Exception) {
            Log.d(this::class.simpleName, "Could not decode mediaSources", e)
        }
        return null
    }

    override fun saveMediaSourceConfigurations(mediaSourceConfigurations : List<MediaSourceConfiguration>?)  {
        val toSave = gson.toJson(mediaSourceConfigurations)
        sharedPreferences.edit {
            putString(KEY_MEDIA_SOURCE_CONFIGURATIONS, toSave)
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
}