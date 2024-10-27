package com.edanthom.vineboomapp

import android.content.Context
import android.content.SharedPreferences
import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.edanthom.vineboomapp.ui.theme.VineBoomAppTheme
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {



        super.onCreate(savedInstanceState)
        setContent {
            VineBoomAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    MainScreen()
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun MainScreen(){
    VineBoomAppTheme {

        Column (
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center // Center items vertically
        ) {
            AddTileBtn()
            Spacer(modifier = Modifier.size(100.dp, 20.dp))
            VolumeMenu()
        }



    }
}

@Composable
private fun AddTileBtn() {
    //Add Tile Button
    val context = LocalContext.current
    Button(
        onClick = {
            //TODO: Add the addtile NONSENSE with AddTileService

            Toast.makeText(context, "This doesn't do anything yet!", Toast.LENGTH_SHORT).show()
        },
    ) {
        Text(text = "Add Tile")

    }
}

fun saveVolume(context: Context, volume: Int) {
    val sharedPref: SharedPreferences = context.getSharedPreferences("volume_prefs", Context.MODE_PRIVATE)
    with(sharedPref.edit()) {
        putInt(context.getString(R.string.saved_tile_volume), volume)
        apply()
    }
}

@Preview(showBackground = true)
@Composable
fun VolumeMenu(){
    VineBoomAppTheme {
        val context = LocalContext.current
        val sharedPref: SharedPreferences = context.getSharedPreferences("volume_prefs", Context.MODE_PRIVATE)
        var volume by remember { mutableIntStateOf(50) }


        val mediaPlayer = remember { MediaPlayer.create(context, R.raw.boom_short) }
        DisposableEffect(Unit) {
            onDispose {
                mediaPlayer.release()
            }
        }



        Column {
            Text(text = "Current Volume: $volume")
            Button(onClick = {
                if (volume < 100) {
                    volume += 5
                    mediaPlayer.setVolume(volume / 100f, volume / 100f)
                    mediaPlayer.start()


                    saveVolume(context, volume)
                }


            }) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.volume_incr), // Replace with your vector ID
                    contentDescription = "Increase volume"
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(text = "Increase volume")


                //SharedPref, save vol

            }

            Button(onClick = {
                if (volume > 0) {
                    volume -= 5
                    mediaPlayer.setVolume(volume / 100f, volume / 100f)
                    mediaPlayer.start()

                    saveVolume(context, volume)
                }

            }) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.volume_dec), // Replace with your vector ID
                    contentDescription = "Decrease volume"
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(text = "Decrease volume")
                //SharedPref, save vol
            }
        }


    //        SharedPreferences.Editor.
//
//        val volume = SharedPreferences
//        Text(text = "Current Volume " + volume)
    }
}