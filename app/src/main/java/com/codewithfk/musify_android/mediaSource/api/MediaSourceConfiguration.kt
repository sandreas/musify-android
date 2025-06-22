package com.codewithfk.musify_android.mediaSource.api

import okhttp3.internal.immutableListOf
import kotlinx.serialization.*
import kotlinx.serialization.json.Json

@Serializable
data class MediaSourceConfiguration(
    val id: String = "",
    val type: String? = this::class.simpleName,
    val mediaTypes: List<MediaSourceMediaType> = immutableListOf(),
    val url: String = "",
    val username: String = "",
    val password: String = "",
    val token: String = "",
    val extraValues: Map<String, String> = mapOf()
)
