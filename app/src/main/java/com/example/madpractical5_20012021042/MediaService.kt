package com.example.madpractical5_20012021042

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder

class MediaService : Service() {
    private lateinit var media:MediaPlayer
    companion object{
        public final val DATA_KEY="service"
        public final val DATA_VALUE="play/pause"
    }
    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (!this::media.isInitialized)
            media = MediaPlayer.create(this, R.raw.song)
        if (intent != null) {
            val str = intent.getStringExtra(DATA_KEY)
            if (str == DATA_VALUE) {
                if (!media.isPlaying) {
                    media.start()
                } else {
                    media.pause()
                }
            }
        }

        return START_STICKY
    }
    override fun onDestroy() {
        media.stop()
        super.onDestroy()
    }
}