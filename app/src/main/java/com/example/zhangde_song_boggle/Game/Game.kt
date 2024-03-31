package com.example.zhangde_song_boggle.Game

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.zhangde_song_boggle.R
import java.io.BufferedReader
import java.io.File
import java.io.InputStream
import java.io.InputStreamReader

class Game{
    private val boardSize = 5
    private var board = Array(boardSize) { CharArray(boardSize) }
    private var visited = Array(boardSize) { BooleanArray(boardSize) }
    var score = 0
    val dictionary =  mutableSetOf<String>()
    private var lastX = -1
    private var lastY = -1
    var word = ""
    private var ansSet = mutableSetOf<String>()

    fun loadDictionary(context : Context){
        var string: String?
        var reader: BufferedReader? = null
        var inputStream: InputStream? = null

        inputStream = context.resources.openRawResource(R.raw.words)
        reader = BufferedReader(InputStreamReader(inputStream))
        while (true) {
            string = reader.readLine()
            Log.d("Load Dictionary", "Add word $string")
            if (string == null) break
            dictionary.add(string)
        }
}

    // Initialize the board with random letters
    init {
        val alphabet = ('A'..'Z').toList()
        for (row in 0 until boardSize) {
            for (col in 0 until boardSize) {
                board[row][col] = alphabet.random()
            }
        }
    }

    fun createBoard(){
        board = Array(boardSize) { CharArray(boardSize) }
        val a = ('A'..'Z').toList()
        for(row in 0 until boardSize){
            for(col in 0 until boardSize){
                board[row][col] = a.random()
            }
        }
    }

    fun resetGame(){
        createBoard()
        score = 0
        clearState()
    }

    fun getBoard():Array<CharArray>{
        return board
    }

    fun submitWord(context: Context): Boolean {

        if (word.length < 4) {
            showToast(context, "Word must be at least 4 characters long.")
            return false
        }

        if(word in ansSet){
            showToast(context, "Word has already be entered before.")
            return false
        }

        if (!isValidWord(context, word)) {
            score -= 10
            if(score<0) score=0
            showToast(context, "Incorrect word! -10 points.")
            return false
        }

        val wordScore = calculateScore(word)
        score += wordScore
        showToast(context, "Correct word! +${wordScore} points.")
        return true
    }

    private fun isValidWord(context: Context, word: String): Boolean {
        if (!dictionary.contains(word.toLowerCase())) return false

        val uniqueLetters = word.toLowerCase()
        val vowels = setOf('a', 'e', 'i', 'o', 'u')
        var vowelCount = 0
        var consonantCount = 0

        for (letter in uniqueLetters) {
            if (letter in vowels) {
                vowelCount++
            } else {
                consonantCount++
            }
        }

        if( vowelCount >= 2 && consonantCount > 0){
            return true
        }
        else{
            showToast(context, "vowel number < 2 or consonant ")
            return false
        }
    }

    private fun calculateScore(word: String): Int {
            ansSet.add(word)
            val consonants = setOf('s', 'z', 'p', 'x', 'q')
            var curScore = 0
            var double =  false
            for (letter in word.toLowerCase()) {
                if (letter in consonants) double=true
                curScore += if (letter in "aeiou") 5 else 1
            }
            if(double) curScore*2
        return score+curScore
    }

    fun isPositionClickable(x2: Int, y2: Int): Boolean {
        // Calculate the absolute difference between x and y coordinates
        if(lastX == -1|| lastY == -1){
            lastX = x2
            lastY = y2
            visited[y2][x2] = true
            word += board[y2][x2].toString()
            return true
        }
        val dx = Math.abs(lastX - x2)
        val dy = Math.abs(lastY - y2)

        // Check if the current position is adjacent to the former position (horizontally, vertically, or diagonally)
        if ((dx == 0 && dy == 1) || (dx == 1 && dy == 0) || (dx == 1 && dy == 1)) {
            // Check if the current position has not been visited before
            if (!visited[y2][x2]) {
                lastX = x2
                lastY = y2
                visited[y2][x2] = true
                word += board[y2][x2].toString()
                return true
            }
        }
        return false
    }

    fun clearState(){
        visited = Array(boardSize) { BooleanArray(boardSize) }
        lastX = -1
        lastY = -1
        word = ""
    }



    fun showToast(context: Context, str : String){
        Toast.makeText(context, str, Toast.LENGTH_SHORT)
    }


}