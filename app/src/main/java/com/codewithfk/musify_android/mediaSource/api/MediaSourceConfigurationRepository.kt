package com.codewithfk.musify_android.mediaSource.api

import com.codewithfk.musify_android.data.MusifySession
import com.codewithfk.musify_android.mediaSource.implementation.audiobookshelf.AudioBookShelfMediaSource
import org.koin.core.annotation.Single

// todo: extract MediaSourceConfigurationStorageInterface instead of Musifysession
@Single
class MediaSourceConfigurationRepository(private val musifySession: MusifySession) {
    var mediaSourceConfigurations:ArrayList<MediaSourceConfiguration> = ArrayList<MediaSourceConfiguration>()
    val mediaSources = ArrayList<MediaSourceInterface>()
    init {
        initialize()
        /*


         */
    }


    fun initialize() {
        val storedConfigs = musifySession.loadMediaSourceConfigurations();
        if(storedConfigs != null) {
            mediaSourceConfigurations = ArrayList(storedConfigs)
        }
        reloadMediaSourcesFromConfigurations();
    }

    fun reloadMediaSourcesFromConfigurations() {
        mediaSourceConfigurations.forEach { it ->
            val tmpMediaSource = buildMediaSource(it)
            if(tmpMediaSource != null) {
                mediaSources.add(tmpMediaSource)
            }
        }
    }

    fun activateMediaSource(mediaSourceConfiguration: MediaSourceConfiguration) {
        return activateMediaSource(mediaSourceConfiguration.id)
    }

    fun activateMediaSource(id: String) {
        mediaSources.forEach { it -> it.active = id == it.id }
    }

    fun insert(mediaSourceConfiguration: MediaSourceConfiguration) {
        mediaSourceConfigurations.add(mediaSourceConfiguration)
    }

    fun delete(mediaSourceConfiguration: MediaSourceConfiguration) {
        delete(mediaSourceConfiguration.id)
    }

    fun delete(id: String) {

    }

    fun update(mediaSourceConfiguration: MediaSourceConfiguration) {
        update(mediaSourceConfiguration, mediaSourceConfiguration.id)
    }
    fun update(mediaSourceConfiguration: MediaSourceConfiguration, oldId: String) {

    }

    fun buildMediaSource(config: MediaSourceConfiguration): MediaSourceInterface? {
        var mediaSource : MediaSourceInterface?
        when(config.type) {
            AudioBookShelfMediaSource::class.simpleName -> {
                mediaSource = AudioBookShelfMediaSource(config.id, config.name)
            } else -> {
            mediaSource = null
        }
        }
        if(mediaSource == null) {
            return null
        }
        var returnValue: MediaSourceInterface? = null
        if(mediaSource.configure(config)) {
            return mediaSource
        }
        return returnValue
    }

}