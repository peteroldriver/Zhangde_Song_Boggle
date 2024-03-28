package com.example.zhangde_song_boggle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.zhangde_song_boggle.Game.Game
import com.example.zhangde_song_boggle.ui.theme.Zhangde_Song_BoggleTheme
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var game = Game()
        game.loadDictionary(applicationContext)
        setContent {
            Zhangde_Song_BoggleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        ButtonsUI(game)
                    }
                }
            }
        }
    }
}
