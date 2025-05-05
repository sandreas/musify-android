package com.codewithfk.musify_android.data.repository

import com.codewithfk.musify_android.data.model.Song
import com.codewithfk.musify_android.data.network.ApiService
import com.codewithfk.musify_android.data.network.Resource
import org.koin.core.annotation.Single

@Single
class MusicRepository(private val apiService: ApiService) {
    suspend fun getSongById(id: String): Resource<Song> {
        return try {
            val response = apiService.getSongById(id)
            if (response.isSuccessful) {
                Resource.Success(response.body()!!)
            } else {
                Resource.Error("Failed to fetch song data")
            }
        } catch (e: Exception) {
            Resource.Error("Network error: ${e.message}")
        }
    }

}