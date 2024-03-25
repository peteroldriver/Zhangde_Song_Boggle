package com.example.zhangde_song_boggle

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.BoxScopeInstance.align
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import com.example.zhangde_song_boggle.ui.theme.Zhangde_Song_BoggleTheme

    @Composable
    fun ButtonsUI(){
        Zhangde_Song_BoggleTheme {
            Column(modifier = Modifier.align(LineHeightStyle.Alignment.CenterVertically)) {
                Greeting("Android")
                Row {
                    LetterButton(letter = "A")
                    LetterButton(letter = "B")
                    LetterButton(letter = "A")
                    LetterButton(letter = "B")
                }
                Row {
                    LetterButton(letter = "A")
                    LetterButton(letter = "B")
                    LetterButton(letter = "A")
                    LetterButton(letter = "B")
                }
                Row {
                    LetterButton(letter = "A")
                    LetterButton(letter = "B")
                    LetterButton(letter = "A")
                    LetterButton(letter = "B")
                }
                Row {
                    LetterButton(letter = "A")
                    LetterButton(letter = "B")
                    LetterButton(letter = "A")
                    LetterButton(letter = "B")
                }
                Text(text = "____",
                     )
            }
        }
    }

    @Composable
    fun Greeting(name: String, modifier: Modifier = Modifier) {
        Text(
            text = "Enter Hit Then Click Submit",
            modifier = modifier
        )
    }

    @Composable
    fun LetterButton(letter: String){
        val buttonColor = remember {
            mutableStateOf(Color.Blue)
        }
        Button(
            onClick = {buttonColor.value = Color.Gray},
            colors = ButtonDefaults.buttonColors(
                Color.Transparent
            ),
            modifier = Modifier
                .background(buttonColor.value)
                .border(BorderStroke(5.dp, Color.White))
                .padding(4.dp) // Add padding for the button
        ) {
            Text(
                text = letter,
                color = Color.Black
            )
        }
    }

