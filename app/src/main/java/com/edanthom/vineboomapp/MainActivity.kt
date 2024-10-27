package com.edanthom.vineboomapp

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
import android.app.StatusBarManager
import android.content.SharedPreferences
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.compose.ui.platform.LocalContext

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
        //Add Tile Button
        val context = LocalContext.current
        Button(onClick = {
            Toast.makeText(context, "This doesn't do anything yet!", Toast.LENGTH_SHORT).show()
        },) {
            Text(text = "Add Tile")

        }
    }
}

@Preview(showBackground = true)
@Composable
fun VolumeMenu(){
    VineBoomAppTheme {
//        SharedPreferences.Editor.
//
//        val volume = SharedPreferences
//        Text(text = "Current Volume " + volume)
    }
}