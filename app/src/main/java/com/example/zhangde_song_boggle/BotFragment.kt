package com.example.zhangde_song_boggle

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun ScoreText() {
    Text(text = "Score")

}

@Composable
fun NewGameButton(){
    val context = LocalContext.current
    Button(
        onClick = { Toast.makeText(context, "NewGame Button clicked!", Toast.LENGTH_SHORT).show()},
        colors = ButtonDefaults.buttonColors(
            Color.Transparent
        ),
        modifier = Modifier
            .background(Color.Transparent)
            .border(BorderStroke(5.dp, Color.White))
            .padding(4.dp) // Add padding for the button
    ) {
        Text(
            text = "New Game",
            color = Color.Black
        )
    }
}

