package com.codewithfk.musify_android.mediaSource.api

import com.codewithfk.musify_android.data.MusifySession
import com.codewithfk.musify_android.mediaSource.api.interfaces.MediaSourceInterface
import com.codewithfk.musify_android.mediaSource.implementation.audiobookshelf.AudioBookShelfMediaSource
import org.koin.core.annotation.Single

// todo: extract MediaSourceConfigurationStorageInterface instead of Musifysession
@Single
class MediaSourceConfigurationRepository(private val musifySession: MusifySession) {
    /*
    fun reloadMediaSourcesFromConfigurations() {
        mediaSourceConfigurations.forEach { it ->
            val tmpMediaSource = buildMediaSource(it)
            if(tmpMediaSource != null) {
                mediaSources.add(tmpMediaSource)
            }
        }
    }

     */


    var mediaSourceConfigurations:ArrayList<MediaSourceConfiguration> = ArrayList<MediaSourceConfiguration>()
    val mediaSources = ArrayList<MediaSourceInterface>()
    init {
        initialize()
    }

    fun index(): List<MediaSourceConfiguration> {
        return mediaSourceConfigurations
    }

    fun getById(id:String): MediaSourceConfiguration? {
        return mediaSourceConfigurations.find { it -> it.id == id }
    }

    fun initialize() {
        val storedConfigs = musifySession.loadMediaSourceConfigurations()
        if(storedConfigs != null) {
            mediaSourceConfigurations = ArrayList(storedConfigs)
        }
    }
    fun getActiveMediaSource(): MediaSourceInterface? {
        val activeConfig = mediaSourceConfigurations.find { it -> it.active}
        if(activeConfig != null) {
            return buildMediaSource(activeConfig)
        }
        return null;
    }




    fun activateMediaSource(mediaSourceConfiguration: MediaSourceConfiguration) {
        return activateMediaSource(mediaSourceConfiguration.id)
    }

    fun activateMediaSource(id: String) {
        mediaSourceConfigurations.forEach { it -> it.active = id == it.id }
        persistMediaSourceConfigurations()
    }

    fun insert(mediaSourceConfiguration: MediaSourceConfiguration) {
        mediaSourceConfigurations.add(mediaSourceConfiguration)
        if(mediaSourceConfiguration.active) {
            activateMediaSource(mediaSourceConfiguration.id)
        }
        persistMediaSourceConfigurations()
    }

    fun delete(mediaSourceConfiguration: MediaSourceConfiguration) {
        delete(mediaSourceConfiguration.id)
    }

    fun delete(id: String) {
        mediaSourceConfigurations.removeIf { it -> it.id == id }
        persistMediaSourceConfigurations()
    }

    fun update(mediaSourceConfiguration: MediaSourceConfiguration) {
        update(mediaSourceConfiguration, mediaSourceConfiguration.id)
    }
    fun update(mediaSourceConfiguration: MediaSourceConfiguration, oldId: String) {
        delete(oldId)
        insert(mediaSourceConfiguration)
        persistMediaSourceConfigurations()
    }

    private fun persistMediaSourceConfigurations() {
        musifySession.saveMediaSourceConfigurations(mediaSourceConfigurations)
    }

    fun buildMediaSource(config: MediaSourceConfiguration): MediaSourceInterface? {
        return when(config.type) {
            AudioBookShelfMediaSource::class.simpleName -> {
                AudioBookShelfMediaSource(config.id, config.name)
            } else -> {
                null
            }
        }?.configure(config)
    }

}