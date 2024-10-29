package com.edanthom.vineboomapp

import android.app.Dialog
import android.media.MediaPlayer
import android.service.quicksettings.Tile
import android.service.quicksettings.TileService
import java.lang.Thread.sleep
import android.os.Handler
import android.os.Looper

class MyQSTileService: TileService() {

    private var mediaPlayer: MediaPlayer? = null
    var booms = 0
    var isBooming = false
    private val handler = Handler(Looper.getMainLooper())



    // Called when the user adds your tile.
    override fun onTileAdded() {
//        mediaPlayer = MediaPlayer.create(this, R.raw.vine_boom)
        super.onTileAdded()
    }
    // Called when your app can update your tile.
    override fun onStartListening() {
//        mediaPlayer = MediaPlayer.create(this, R.raw.vine_boom)
        super.onStartListening()
    }

    // Called when your app can no longer update your tile.
    override fun onStopListening() {
        mediaPlayer?.release()
        mediaPlayer = null
        super.onStopListening()
    }

    // Called when the user taps on your tile in an active or inactive state.
    override fun onClick() {
        super.onClick()
        booms++

        if (booms > 10 && !isBooming) {

            booms = 0
            isBooming = true
            qsTile.state = Tile.STATE_ACTIVE;
            qsTile.label = "BOOM!"
            qsTile.updateTile()

            //wait 5 seconds

            handler.postDelayed({
                isBooming = false
                qsTile.state = Tile.STATE_INACTIVE
                qsTile.label = "Vine Boom"
                qsTile.updateTile()
            }, 5000)


        }


        mediaPlayer = MediaPlayer.create(this, R.raw.vine_boom).apply {
            setOnCompletionListener {
                // Release resources after sound completes
                release()
                mediaPlayer = null
            }
            start()
        }
    }

//    // Release MediaPlayer resources to prevent memory leaks
//    override fun onDestroy() {
//        mediaPlayer?.release()
//        mediaPlayer = null
//        super.onDestroy()
//    }

    // Called when the user removes your tile.
    override fun onTileRemoved() {
        mediaPlayer?.release()
        mediaPlayer = null
        super.onTileRemoved()
    }
}