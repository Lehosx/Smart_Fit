package com.lehos.musicplayer

import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.MediaPlayer
import android.os.*
import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.MediaSessionCompat
import android.support.v4.media.session.PlaybackStateCompat
import androidx.core.app.NotificationCompat
import com.lehos.musicplayer.service.App

class MusicService: Service() {
    private var myBinder = MyBinder()
    var mediaPlayer:MediaPlayer? =null
    private lateinit var mediaSession : MediaSessionCompat
    private lateinit var runnable: Runnable

    override fun onBind(intent: Intent?): IBinder {
        mediaSession = MediaSessionCompat(baseContext, "My Music")
        return myBinder
    }

    inner class MyBinder:Binder(){
        fun currentService(): MusicService{
            return this@MusicService
        }
    }



    fun showNotification(playPauseBtn: Int){
        //val intent = Intent(baseContext, MainActivity::class.java)

        val flag = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            PendingIntent.FLAG_IMMUTABLE
        } else {
            PendingIntent.FLAG_UPDATE_CURRENT
        }

        //val contentIntent = PendingIntent.getActivity(this, 0, intent, flag)

        val prevIntent = Intent(baseContext, NotificationReceiver::class.java).setAction(App.PREVIOUS)
        val prevPendingIntent = PendingIntent.getBroadcast(baseContext, 0 , prevIntent, flag)

        val playIntent = Intent(baseContext, NotificationReceiver::class.java).setAction(App.PLAY)
        val playPendingIntent = PendingIntent.getBroadcast(baseContext, 0 , playIntent, flag)

        val nextIntent = Intent(baseContext, NotificationReceiver::class.java).setAction(App.NEXT)
        val nextPendingIntent = PendingIntent.getBroadcast(baseContext, 0 , nextIntent, flag)

        val exitIntent = Intent(baseContext, NotificationReceiver::class.java).setAction(App.EXIT)
        val exitPendingIntent = PendingIntent.getBroadcast(baseContext, 0 , exitIntent, flag)
//        val intent = Intent("close_app")
//        val exitPendingIntent = PendingIntent.getBroadcast(
//            this,
//            System.currentTimeMillis().toInt(),
//            intent,
//            PendingIntent.FLAG_UPDATE_CURRENT
//        )

        val imgArt = getImgArt(PlayerActivity.musicListPA[PlayerActivity.songPosition].path)
        val image = if (imgArt != null) {
            BitmapFactory.decodeByteArray(imgArt, 0 ,imgArt.size)
        }else{
            BitmapFactory.decodeResource(resources, R.drawable.splash_screen)
        }

        val notification = NotificationCompat.Builder(baseContext, App.CHANNEL_ID)
            .setContentTitle(PlayerActivity.musicListPA[PlayerActivity.songPosition].title)
            .setContentText(PlayerActivity.musicListPA[PlayerActivity.songPosition].artist)
            .setSmallIcon(R.drawable.music_icon)
            .setLargeIcon(image)
            .setStyle(androidx.media.app.NotificationCompat.MediaStyle().setMediaSession(mediaSession.sessionToken))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setOnlyAlertOnce(true)
            .addAction(R.drawable.previous_icon, "previous", prevPendingIntent)
            .addAction(playPauseBtn, "play", playPendingIntent)
            .addAction(R.drawable.next_icon, "next", nextPendingIntent)
            .addAction(R.drawable.exit_icon, "exit", exitPendingIntent)
            .build()

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val playbackSpeed = if (PlayerActivity.isPlaying) 1F else 0F
            mediaSession.setMetadata(
                MediaMetadataCompat.Builder()
                    .putLong(
                        MediaMetadataCompat.METADATA_KEY_DURATION,
                        mediaPlayer!!.duration.toLong()
                    )
                    .build()
            )
            val playBackState = PlaybackStateCompat.Builder()
                .setState(
                    PlaybackStateCompat.STATE_PLAYING,
                    mediaPlayer!!.currentPosition.toLong(),
                    playbackSpeed
                )
                .setActions(PlaybackStateCompat.ACTION_SEEK_TO)
                .build()
            mediaSession.setPlaybackState(playBackState)
        }

        startForeground(13, notification)
    }

    fun createMediaPlayer(){
        try{
            if(PlayerActivity.musicService!!.mediaPlayer == null) PlayerActivity.musicService!!.mediaPlayer = MediaPlayer()
            PlayerActivity.musicService!!.mediaPlayer!!.reset()
            PlayerActivity.musicService!!.mediaPlayer!!.setDataSource(PlayerActivity.musicListPA[PlayerActivity.songPosition].path)
            PlayerActivity.musicService!!.mediaPlayer!!.prepare()
            PlayerActivity.binding.playPauseBtnPA.setIconResource(R.drawable.pause_icon)
            PlayerActivity.musicService!!.showNotification(R.drawable.pause_icon)
            PlayerActivity.binding.tvSeekBarStart.text = formatDuration(mediaPlayer!!.currentPosition.toLong())
            PlayerActivity.binding.tvSeekBarEnd.text = formatDuration(mediaPlayer!!.duration.toLong())
            PlayerActivity.binding.seekbarPA.progress = 0
            PlayerActivity.binding.seekbarPA.max = mediaPlayer!!.duration
            PlayerActivity.nowPlayingID = PlayerActivity.musicListPA[PlayerActivity.songPosition].id
        }
        catch (e:Exception){
            return
        }
    }

    fun seekBarSetup(){
        runnable = Runnable {
                PlayerActivity.binding.tvSeekBarStart.text =
                    formatDuration(mediaPlayer!!.currentPosition.toLong())
                PlayerActivity.binding.seekbarPA.progress = mediaPlayer!!.currentPosition
                Handler(Looper.getMainLooper()).postDelayed(runnable, 200)
        }
        Handler(Looper.getMainLooper()).postDelayed(runnable,0)
    }

}