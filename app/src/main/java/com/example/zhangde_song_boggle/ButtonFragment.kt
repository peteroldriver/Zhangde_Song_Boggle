package com.example.zhangde_song_boggle

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.zhangde_song_boggle.Game.Game

@Composable
fun ButtonsUI(context: Context, game: Game) {
    // State to hold the text value
    var text by remember { mutableStateOf(game.word) }
    var board by remember {mutableStateOf(game.getBoard())}
    val buttonColors = remember { mutableStateOf(List(5) { List(5) { Color.Blue } }) }
    var score by remember { mutableIntStateOf(0) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(16.dp)
    ) {
        Greeting(name = "Hit BUttons to Form Words")
        for (i in 0 until 5) {
            Row {
                for (j in 0 until 5) {
                    LetterButton(i, j, game, board, buttonColors) { newWord ->
                        // Update text when a button is clicked
                        text += newWord.toString()
                    }
                }
            }
        }
        Text(
            text = text,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        // Add your ClearButton and SubmitButton here
        Row {
            ClearButton(resetState = {
                text = ""
                game.clearState()
                buttonColors.value = List(5) { List(5) { Color.Blue } }
            })
            SubmitButton(onSubmitClicked = {
                game.submitWord(context)
                score = game.score
                text = ""
                game.clearState()
                buttonColors.value = List(5) { List(5) { Color.Blue } }
            }
            )
        }
    }
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            ScoreText() // ScoreText takes the left side of the first line
            ScoreNum(num = score)
            Spacer(modifier = Modifier.weight(1f)) // Add a spacer to push the NewGameButton to the right

        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.weight(1f)) // Add a spacer to push the ScoreText to the left
            NewGameButton(newGame = {
                game.resetGame()
                board = game.getBoard()
                score = game.score
                buttonColors.value = List(5) { List(5) { Color.Blue } }
                text = game.word
            }) // NewGameButton takes the right side of the second line
        }
    }
}

    @Composable
    fun Greeting(name: String, modifier: Modifier = Modifier) {
        Text(
            text = "Enter Hit Then Click Submit",
            modifier = modifier,
            fontSize = 32.sp
        )
    }

@Composable
fun LetterButton(
    y: Int,
    x: Int,
    game: Game,
    board: Array<CharArray>,
    buttonColors: MutableState<List<List<Color>>>,
    param: (Any) -> Unit
){
    Button(
        onClick = {
            if(game.isPositionClickable(x, y)){
                buttonColors.value = buttonColors.value.mapIndexed { rowIndex, row ->
                    row.mapIndexed { colIndex, color ->
                        if (rowIndex == y && colIndex == x) {
                            Color.Gray
                        } else {
                            color
                        }
                    }
                }
                param(board[y][x].toString())
            }
        },
        colors = ButtonDefaults.buttonColors(
            Color.Transparent
        ),
        modifier = Modifier
            .background(buttonColors.value[y][x])
            .border(BorderStroke(5.dp, Color.White))
            .padding(4.dp) // Add padding for the button
    ) {
        Text(
            text = game.getBoard()[y][x].toString(),
            color = Color.Black,
            fontSize = 24.sp
        )
    }
}

@Composable
fun ClearButton(resetState: () -> Unit) {
    val context = LocalContext.current

    Button(
        onClick = {
            // Reset the state
            resetState()
            // Show a toast indicating the text is cleared
            Toast.makeText(context, "Text Cleared", Toast.LENGTH_SHORT).show()
        },
        colors = ButtonDefaults.buttonColors(Color.Transparent),
        modifier = Modifier.padding(4.dp)
    ) {
        Text(
            text = "Clear",
            color = Color.Black,
            fontSize = 32.sp
        )
    }
}

    @Composable
    fun SubmitButton(onSubmitClicked: () -> Unit){
        val context = LocalContext.current
        Button(
            onClick = {
                onSubmitClicked()
                Toast.makeText(context, "Submit Button clicked!", Toast.LENGTH_SHORT).show()
                      },
            colors = ButtonDefaults.buttonColors(
                Color.Transparent
            ),
            modifier = Modifier
                .background(Color.Transparent)
                .border(BorderStroke(5.dp, Color.White))
                .padding(4.dp) // Add padding for the button
        ) {
            Text(
                text = "Submit",
                color = Color.Black,
                fontSize = 32.sp
            )
        }
    }


