package com.codewithfk.musify_android.mediaSource.api

import okhttp3.internal.immutableListOf
import kotlinx.serialization.*
import kotlinx.serialization.json.Json

@Serializable
data class MediaSourceConfiguration(
    val id: String = "",
    val name: String = "",
    val type: String? = this::class.simpleName,
    val mediaTypes: List<MediaSourceMediaType> = immutableListOf(),
    val url: String = "",
    val username: String = "",
    val password: String = "",
    val token: String = "",
    var active: Boolean = false,
    val extraValues: Map<String, String> = mapOf()
)
