package com.codewithfk.musify_android.data

import android.content.Context
import org.koin.core.annotation.Single
import androidx.core.content.edit
import com.codewithfk.musify_android.mediaSource.api.MediaSourceConfiguration
import com.codewithfk.musify_android.mediaSource.api.MediaSourceInterface
import com.codewithfk.musify_android.mediaSource.api.MediaSourceMediaType
import com.codewithfk.musify_android.mediaSource.implementation.mock.MockMediaSource
import com.google.gson.Gson

@Single
class MusifySession(private val context: Context) {
    private val sharedPreferences =
        context.getSharedPreferences("musify_session", Context.MODE_PRIVATE)
    private val mediaSources = ArrayList<MediaSourceInterface>()
    val gson = Gson()

    init {
        loadMediaSources()
    }

    private fun loadMediaSources() {
        var mediaSourcesJson = sharedPreferences.getString("mediaSources", null)
        if(mediaSourcesJson == null) {
            return
        }

        try {
            val list: List<MediaSourceConfiguration> = gson.fromJson<ArrayList<MediaSourceConfiguration>>(mediaSourcesJson,
                ArrayList::class.java)

                /*Json.decodeFromString(
                MediaSourceConfiguration.serializer(),
                mediaSourcesJson
            );*/
            // mediaSources.addAll();

        } catch (e: Exception) {

        }



        /*
        // serializing objects
        val jsonData = Json.encodeToString(MyModel.serializer(), MyModel(42))
        println(jsonData) // {"a": 42, "b": "42"}

        // serializing lists
        val jsonList = Json.encodeToString(MyModel.serializer().list, listOf(MyModel(42)))
        println(jsonList) // [{"a": 42, "b": "42"}]

        // parsing data back
        val obj = Json.decodeFromString(MyModel.serializer(), """{"a":42}""")
        println(obj) // MyModel(a=42, b="42")

         */
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
        sharedPreferences.edit() {
            clear()
        }
    }

    fun getActiveMediaSource(): MediaSourceInterface {

        return MockMediaSource("mock", "Mock Media Source");
    }
}