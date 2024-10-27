package com.edanthom.vineboomapp

import android.app.Dialog
import android.media.MediaPlayer
import android.service.quicksettings.TileService

class MyQSTileService: TileService() {

    private var mediaPlayer: MediaPlayer? = null

    // Called when the user adds your tile.
    override fun onTileAdded() {
        super.onTileAdded()
    }
    // Called when your app can update your tile.
    override fun onStartListening() {
        super.onStartListening()
    }

    // Called when your app can no longer update your tile.
    override fun onStopListening() {
        super.onStopListening()
    }

    // Called when the user taps on your tile in an active or inactive state.
    override fun onClick() {
        super.onClick()

        mediaPlayer = MediaPlayer.create(this, R.raw.vine_boom)
        mediaPlayer?.start()
    }

    // Release MediaPlayer resources to prevent memory leaks
    override fun onDestroy() {
        mediaPlayer?.release()
        mediaPlayer = null
        super.onDestroy()
    }

    // Called when the user removes your tile.
    override fun onTileRemoved() {
        super.onTileRemoved()
    }
}