package com.codewithfk.musify_android.data.service

import android.app.Notification
import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.MediaSessionCompat
import android.support.v4.media.session.PlaybackStateCompat
import android.util.Log
import android.view.KeyEvent
import androidx.core.net.toUri
import androidx.media.session.MediaButtonReceiver
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.codewithfk.musify_android.data.helper.MusifyNotificationHelper
import com.codewithfk.musify_android.data.model.Song
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import java.util.Timer
import kotlin.concurrent.timerTask

class MusifyPlaybackService : Service() {

    companion object {
        const val ACTION_PLAY = "com.codewithfk.musify_android.ACTION_PLAY"
        const val ACTION_PAUSE = "com.codewithfk.musify_android.ACTION_PAUSE"
        const val ACTION_STOP = "com.codewithfk.musify_android.ACTION_STOP"
        const val ACTION_PREVIOUS = "com.codewithfk.musify_android.ACTION_PREVIOUS"
        const val ACTION_NEXT = "com.codewithfk.musify_android.ACTION_NEXT"
        const val ACTION_PREPARE_SONG = "com.codewithfk.musify_android.ACTION_PREPARE_SONG"

        val KEY_SONG = "SONG"
    }

    inner class MusicBinder : Binder() {
        fun getService(): MusifyPlaybackService = this@MusifyPlaybackService
    }

    private val binder = MusicBinder()
    private val serviceScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    private val _player = MutableStateFlow<PlayerState>(PlayerState())
    val playerState = _player.asStateFlow()

    private lateinit var exoPlayer: ExoPlayer
    private lateinit var mediaSession: MediaSessionCompat
    private val notificationHelper: MusifyNotificationHelper by inject()

    private var positionUpdateJob: Job? = null
    private var notificationUpdateJob: Job? = null

    val playerListener = object : Player.Listener {
        override fun onPlaybackStateChanged(playbackState: Int) {
            when (playbackState) {
                Player.STATE_BUFFERING -> {
                    _player.value = playerState.value.copy(
                        isBuffering = true,
                        currentPosition = exoPlayer.currentPosition,
                        duration = exoPlayer.duration,
                        error = null,
                        isPlaying = false
                    )
                    updatePlaybackState(PlaybackStateCompat.STATE_BUFFERING)
                    updateMediaSessionState()
                }

                Player.STATE_READY -> {
                    _player.value = playerState.value.copy(
                        isPlaying = exoPlayer.isPlaying,
                        currentPosition = exoPlayer.currentPosition,
                        duration = exoPlayer.duration,
                        error = null,
                        isBuffering = false
                    )

                    if (exoPlayer.isPlaying) {
                        startForegroundServiceIfNeeded()
                        updatePlaybackState(PlaybackStateCompat.STATE_PLAYING)
                    } else {
                        updatePlaybackState(PlaybackStateCompat.STATE_PAUSED)
                    }
                    updateMediaSessionState()
                }

                Player.STATE_ENDED -> {
                    _player.value = playerState.value.copy(
                        isPlaying = false,
                        currentPosition = 0L,
                        duration = 0L,
                        error = null,
                        isBuffering = false
                    )
                    updatePlaybackState(PlaybackStateCompat.STATE_STOPPED)
                    updateMediaSessionState()
                }

                Player.STATE_IDLE -> {
                    _player.value = playerState.value.copy(
                        isPlaying = false,
                        currentPosition = 0L,
                        duration = 0L,
                        error = null,
                        isBuffering = false
                    )
                    updatePlaybackState(PlaybackStateCompat.STATE_NONE)
                    updateMediaSessionState()
                }
            }
        }
    }

    private fun updatePlaybackState(state: Int) {
        val position = exoPlayer.currentPosition
        val state = PlaybackStateCompat.Builder().setState(state, position, 1f)
            .setActions(
                PlaybackStateCompat.ACTION_PLAY or PlaybackStateCompat.ACTION_PAUSE or PlaybackStateCompat.ACTION_STOP or PlaybackStateCompat.ACTION_SEEK_TO or PlaybackStateCompat.ACTION_SKIP_TO_NEXT or PlaybackStateCompat.ACTION_SKIP_TO_PREVIOUS
            ).build()
        mediaSession.setPlaybackState(state)
    }

    private fun updateMediaSessionState() {
        if (exoPlayer.isPlaying || _player.value.currentSong != null) {
            if (!mediaSession.isActive) {
                mediaSession.isActive = true
            }
        } else {
            if (mediaSession.isActive) {
                mediaSession.isActive = false
            }
        }
    }


    val mediaSessionCallBack = object : MediaSessionCompat.Callback() {
        override fun onPlay() {
            resumeSong()
        }

        override fun onPause() {
            pauseSong()
        }

        override fun onStop() {
            super.onStop()
        }

        override fun onSkipToNext() {
            super.onSkipToNext()
        }

        override fun onSkipToPrevious() {
            super.onSkipToPrevious()
        }

        override fun onSeekTo(pos: Long) {
            super.onSeekTo(pos)
            exoPlayer.seekTo(pos)
            _player.value = playerState.value.copy(
                currentPosition = pos,
                duration = exoPlayer.duration,
                isBuffering = exoPlayer.isLoading,
                isPlaying = exoPlayer.isPlaying,
                error = null
            )
        }

        /* CUSTOM sandreas! */
        fun onSeek(offset: Long) {
            val currentPos = exoPlayer.currentPosition
            var newPosition = currentPos + offset
            if (newPosition < 0) {
                newPosition = 0;
            } else if (newPosition > exoPlayer.duration) {
                newPosition = exoPlayer.duration - 1;
            }
            super.onSeekTo(newPosition)
            exoPlayer.seekTo(newPosition)
            _player.value = playerState.value.copy(
                currentPosition = newPosition,
                duration = exoPlayer.duration
            )
        }


        val tag = "MediaSessionCompat.Callback()"
        val seekPlayBufferTime = 450L

        var clickPressed = false
        var clickCount = 0
        var clickTimer = Timer()
        var clickTimerScheduled = false
        var clickTimerId = System.currentTimeMillis()
        var lastStatePlaying = false
        var stopSeeking = false

        override fun onMediaButtonEvent(intent: Intent): Boolean {
            if (Intent.ACTION_MEDIA_BUTTON != intent.action) {
                return false;
            }
            val keyEvent = if (Build.VERSION.SDK_INT >= 33) {
                intent.getParcelableExtra(Intent.EXTRA_KEY_EVENT, KeyEvent::class.java)
            } else {
                @Suppress("DEPRECATION")
                intent.getParcelableExtra(Intent.EXTRA_KEY_EVENT)
            }
            if(keyEvent == null) {
                return false
            }

            // Log.d(tag, "=== debounceKeyEvent: ${keyEventToString(keyEvent)}")
            return debounceKeyEvent(keyEvent)
        }

        private fun keyEventToString(keyEvent: KeyEvent): String {
            var action = ""
            when (keyEvent.action) {
                KeyEvent.ACTION_UP -> {
                    action = "ACTION_UP"
                }
                KeyEvent.ACTION_DOWN  -> {
                    action = "ACTION_DOWN"
                }
            }

            var keyCode = ""
            when (keyEvent.keyCode) {
                KeyEvent.KEYCODE_HEADSETHOOK -> {
                    keyCode = "KEYCODE_HEADSETHOOK"
                }
                KeyEvent.KEYCODE_MEDIA_PLAY -> {
                    keyCode = "KEYCODE_MEDIA_PLAY"
                }
                KeyEvent.KEYCODE_MEDIA_PAUSE -> {
                    keyCode = "KEYCODE_MEDIA_PAUSE"
                }
                KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE  -> {
                    keyCode = "KEYCODE_MEDIA_PLAY_PAUSE"
                }

                KeyEvent.KEYCODE_MEDIA_NEXT -> {
                    keyCode = "KEYCODE_MEDIA_NEXT"
                }

                KeyEvent.KEYCODE_MEDIA_PREVIOUS -> {
                    keyCode = "KEYCODE_MEDIA_PREVIOUS"
                }

                KeyEvent.KEYCODE_MEDIA_STOP -> {
                    keyCode = "KEYCODE_MEDIA_STOP"
                }
            }

            return "keyCode=$keyCode, action=$action, repeatCount=${keyEvent.repeatCount}"
        }

        private fun debounceKeyEvent(keyEvent: KeyEvent): Boolean {
            Log.d(
                tag,
                "=== debounceKeyEvent: ${keyEventToString(keyEvent)}, clickCount=$clickCount, repeatCount=${keyEvent.repeatCount} longPress=${keyEvent.isLongPress}"
            )


            // ignore repeated events



            // how does this work:
            // - every keyDown and keyUp triggers a scheduled handler
            // - another keyDown or keyUp cancels the scheduled handler and re-triggers it with new values
            // - the handler takes clickCount:int and clickPressed:bool (if held down)
            // - keyCodes increase the number of clicks (PlayPause+=1, Next+=2, Prev+=3)
            // - depending on the number of clicks, the playerNotificationService handles the configured action
            // problems:
            // - the logs show pretty accurate click / hold detection, but it does not really translate well in the player
            // - since the trigger is scheduled, it does run in a different thread
            // - this leads to strange behaviour - probably easy to fix, but I'm no kotlin native (Coroutines)
            // - probably after some actions the thread of the player is no longer accessible...
            if (keyEvent.action == KeyEvent.ACTION_UP) {
                clickPressed = false
                // Log.d(tag, "=== KeyEvent.ACTION_UP")

            } else if (keyEvent.action == KeyEvent.ACTION_DOWN) {
                // Log.d(tag, "=== KeyEvent.ACTION_DOWN")

                if (clickPressed) {
                    return true
                }
                clickPressed = true

                when (keyEvent.keyCode) {
                    KeyEvent.KEYCODE_HEADSETHOOK,
                    KeyEvent.KEYCODE_MEDIA_PLAY,
                    KeyEvent.KEYCODE_MEDIA_PAUSE,
                    KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE -> {
                        clickCount++
                        Log.d(
                            tag,
                            "=== handleCallMediaButton: Headset Hook/Play/ Pause, clickCount=$clickCount"
                        )
                    }

                    KeyEvent.KEYCODE_MEDIA_NEXT -> {
                        clickCount += 2
                        Log.d(tag, "=== handleCallMediaButton: Media Next, clickCount=$clickCount")
                    }

                    KeyEvent.KEYCODE_MEDIA_PREVIOUS -> {
                        clickCount += 3
                        Log.d(
                            tag,
                            "=== handleCallMediaButton: Media Previous, clickCount=$clickCount"
                        )
                    }

                    KeyEvent.KEYCODE_MEDIA_STOP -> {
                        Log.d(tag, "=== handleCallMediaButton: Media Stop, clickCount=$clickCount")
                        onStop()
                        clickTimer.cancel()
                        return true
                    }

                    else -> {
                        Log.d(tag, "=== KeyCode:${keyEvent.keyCode}, clickCount=$clickCount")
                        return false
                    }
                }
            }

            if (clickTimerScheduled) {
                Log.d(
                    tag,
                    "=== clickTimer cancelled: clicks=$clickCount, hold=$clickPressed,  id=$clickTimerId ==== ${keyEventToString(keyEvent)} ===="
                )
                clickTimer.cancel()
                clickTimer.purge() // not required?
                clickTimer = Timer()
            }

            //if(keyEvent.repeatCount == 0) {
            // this executes although it should be cancelled!
            // race condition?
            // replace with: https://stackoverflow.com/questions/50858684/kotlin-android-debounce
            /*
                        serviceScope.launch {

                        }


                        searchJob?.cancel()
                        searchJob = viewModelScope.launch {
                            delay(500)
                            search(text)
                        }

                         */

            clickTimer.schedule(timerTask {
                    Log.d(
                        tag,
                        "=== clickTimer executed: clicks=$clickCount, hold=$clickPressed, id=$clickTimerId ==== ${keyEventToString(keyEvent)} ===="
                    )
                    handleClicks(clickCount, clickPressed)

                    clickCount = 0
                    clickTimerScheduled = false
                }, 650)

            clickTimerScheduled = true
            Log.d(
                tag,
                "== clickTimer scheduled ($clickTimerId): clicks=$clickCount, hold=$clickPressed ==== ${keyEventToString(keyEvent)} ===="
            )
            // }


            return true
        }

        fun handleClicks(clicks: Int, clickPressed: Boolean) {
            Log.d(tag, "=== handleClicks: count=$clicks,hold=$clickPressed")
            return
            stopSeeking = true
            serviceScope.launch {
                // the handlers should be configurlateinitable, defaults:
                // hold -> jumpBackward
                // click -> play / pause
                // click, hold -> fast forward
                // click, click -> next (chapter or track)
                // click, click, hold -> rewind
                // click, click, click -> previous (chapter or track)

                Log.d(tag, "=== handleClicks: count=$clicks,hold=$clickPressed")

                if (clickPressed) {
                    lastStatePlaying = exoPlayer.isPlaying
                    when (clicks) {
                        1 -> {
                            // jumpBackward()
                            onSeek(-30000)
                        }

                        2 -> {

                            Log.d(tag, "=== fastForward init, stopSeeking=$stopSeeking")
                            stopSeeking = false
                            val mainHandler = Handler(mainLooper)
                            mainHandler.post(object : Runnable {
                                override fun run() {
                                    onSeek(10000 - seekPlayBufferTime)
                                    onPlay()
                                    if(!stopSeeking) {
                                        mainHandler.postDelayed(this, seekPlayBufferTime)
                                    }
                                }
                            })
                        }

                        3 -> {
                            Log.d(tag, "=== rewind init, stopSeeking=$stopSeeking")
                            stopSeeking = false
                            val mainHandler = Handler(mainLooper)
                            mainHandler.post(object : Runnable {
                                override fun run() {
                                    onSeek(-(10000 + seekPlayBufferTime))
                                    onPlay()
                                    if(!stopSeeking) {
                                        mainHandler.postDelayed(this, seekPlayBufferTime)
                                    }
                                }
                            })
                        }
                    }
                } else {
                    when (clicks) {
                        0 -> {
                            // switch from fastForward / rewind back to last playing state
                            if (lastStatePlaying) {
                                onPlay()
                            } else {
                                onPause()
                            }
                        }

                        1 -> {
                            if (exoPlayer.isPlaying) {
                                onPause()
                            } else {
                                onPlay()
                            }
                        }

                        2 -> {
                            // todo: implement "next chapter"
                            // workaround: just seek +5mins
                            // skipToNext()
                            // seekForward(300000)
                            onSeek(300000)
                        }

                        3 -> {
                            // todo: implement "previous chapter"
                            // workaround: just seek -5mins
                            // skipToPrevious()
                            // seekBackward(300000)
                            onSeek(-300000)
                        }
                    }
                }
            }



        }

        /* END CUSTOM sandreas */


    }

    override fun onCreate() {
        super.onCreate()

        exoPlayer = ExoPlayer.Builder(this).build().also {
            it.playWhenReady = true
            it.addListener(playerListener)
        }

        mediaSession = MediaSessionCompat(this, "MusifyPlaybackService").also {
            it.isActive = true
            it.setCallback(mediaSessionCallBack)
            it.setPlaybackState(
                PlaybackStateCompat.Builder().setState(PlaybackStateCompat.STATE_NONE, 0, 0f)
                    .setActions(
                        PlaybackStateCompat.ACTION_PLAY or PlaybackStateCompat.ACTION_PAUSE or PlaybackStateCompat.ACTION_STOP or PlaybackStateCompat.ACTION_SEEK_TO or PlaybackStateCompat.ACTION_SKIP_TO_NEXT or PlaybackStateCompat.ACTION_SKIP_TO_PREVIOUS
                    ).build()
            )

        }
        startPositionUpdate()
    }

    private fun startPositionUpdate() {
        positionUpdateJob?.cancel()
        positionUpdateJob = serviceScope.launch {
            while (true) {
                if (exoPlayer.isPlaying) {
                    _player.value = playerState.value.copy(
                        currentPosition = exoPlayer.currentPosition,
                        duration = exoPlayer.duration,
                        isBuffering = exoPlayer.isLoading,
                        isPlaying = exoPlayer.isPlaying,
                        error = null
                    )
                }
                kotlinx.coroutines.delay(500)
            }
        }
    }

    var isForegroundService = false
    var currentNotification: Notification? = null

    fun startForegroundServiceIfNeeded() {
        val currentSong = playerState.value.currentSong ?: return
        if (!isForegroundService) {
            notificationHelper.createPlayerNotification(
                playerState.value.isPlaying, currentSong, mediaSession
            ) {
                if (!isForegroundService) {
                    try {
                        currentNotification = it
                        startForeground(
                            MusifyNotificationHelper.NOTIFICATION_ID, it
                        )
                        isForegroundService = true
                    } catch (ex: Exception) {
                        ex.printStackTrace()
                    }
                } else {
                    currentNotification = it
                    updateNotification()
                }
            }
        } else {
            updateNotification()
        }

    }

    fun updateNotification() {
        notificationUpdateJob?.cancel()
        notificationUpdateJob = serviceScope.launch {
            notificationHelper.createPlayerNotification(
                playerState.value.isPlaying,
                playerState.value.currentSong ?: return@launch,
                mediaSession
            ) {
                try {
                    currentNotification = it
                    notificationHelper.updateNotification(it)
                } catch (ex: Exception) {
                    ex.printStackTrace()
                }
            }
        }
    }

    fun stopForegroundService() {
        if (isForegroundService) {
            try {
                mediaSession.isActive = false
                stopForeground(Service.STOP_FOREGROUND_REMOVE)
                isForegroundService = false
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        if (intent != null) {
            MediaButtonReceiver.handleIntent(mediaSession, intent)
        }
        // Handle the intent actions here
        when (intent?.action) {
            ACTION_PLAY -> {
                val song = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    intent.getParcelableExtra(KEY_SONG, Song::class.java)
                } else {
                    intent.getParcelableExtra<Song>(KEY_SONG)
                }

                if (song != null) {
                    playSong(song)
                } else {
                    if (_player.value.currentSong != null) {
                        resumeSong()
                    }
                }
            }

            ACTION_PAUSE -> {
                pauseSong()
            }

            ACTION_STOP -> {

            }

            ACTION_PREVIOUS -> {

            }

            ACTION_NEXT -> {

            }
        }
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return binder
    }


    fun playSong(song: Song) {
        try {
            _player.value = playerState.value.copy(
                currentSong = song,
                isBuffering = true,
                currentPosition = 0L,
                duration = song.duration.toLong()
            )
            val metaBuilder = MediaMetadataCompat.Builder()
                .putString(MediaMetadataCompat.METADATA_KEY_TITLE, song.title)
                .putString(MediaMetadataCompat.METADATA_KEY_ARTIST, song.artist.name)
                .putString(MediaMetadataCompat.METADATA_KEY_ALBUM_ART_URI, song.coverImage)
                .putString(MediaMetadataCompat.METADATA_KEY_DISPLAY_ICON_URI, song.coverImage)
                .putString(MediaMetadataCompat.METADATA_KEY_MEDIA_ID, song.id)

            mediaSession.setMetadata(metaBuilder.build())
            val mediaItem = MediaItem.fromUri(song.audioUrl.toUri())
            exoPlayer.setMediaItem(mediaItem)
            exoPlayer.prepare()
            exoPlayer.playWhenReady = true
        } catch (ex: Exception) {
            _player.value = playerState.value.copy(
                error = ex.message,
                isBuffering = false,
                currentSong = null
            )
            ex.printStackTrace()
        }
    }

    fun pauseSong() {
        try {
            exoPlayer.pause()
            _player.value = playerState.value.copy(
                isPlaying = false,
                currentPosition = exoPlayer.currentPosition,
                duration = exoPlayer.duration
            )
        } catch (ex: Exception) {
            _player.value = playerState.value.copy(
                error = ex.message,
                isBuffering = false,
                currentSong = null
            )
            ex.printStackTrace()
        }
        updateNotification()
    }
    fun isPlaying(): Boolean {
        return exoPlayer.isPlaying
    }

    fun resumeSong() {
        try {
            exoPlayer.play()
            _player.value = playerState.value.copy(
                isPlaying = true,
                currentPosition = exoPlayer.currentPosition,
                duration = exoPlayer.duration
            )
            startForegroundServiceIfNeeded()
        } catch (ex: Exception) {
            _player.value = playerState.value.copy(
                error = ex.message,
                isBuffering = false,
                currentSong = null
            )
            ex.printStackTrace()
        }
        updateNotification()
    }
}

data class PlayerState(
    val isPlaying: Boolean = false,
    val currentSong: Song? = null,
    val currentPosition: Long = 0L,
    val duration: Long = 0L,
    val error: String? = null,
    val isBuffering: Boolean = false,
)