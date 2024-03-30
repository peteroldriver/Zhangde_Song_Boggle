package com.example.zhangde_song_boggle.Game

import android.content.Context
import android.util.Log
import com.example.zhangde_song_boggle.R
import java.io.BufferedReader
import java.io.File
import java.io.InputStream
import java.io.InputStreamReader

class Game{
    private val boardSize = 4
    private val board = Array(boardSize) { CharArray(boardSize) }
    private var visited = Array(boardSize) { BooleanArray(boardSize) }
    var score = 0
    val dictionary: Set<String> = mutableSetOf<String>()
    private var lastX = -1
    private var lastY = -1
    var word = ""
    private var ansSet: Set<String> = mutableSetOf<String>()

    fun loadDictionary(context : Context){
        var string: String?
        var reader: BufferedReader? = null
        var inputStream: InputStream? = null

        inputStream = context.resources.openRawResource(R.raw.words)
        reader = BufferedReader(InputStreamReader(inputStream))
        while (true) {
            string = reader.readLine()
            if (string == null) break
            dictionary.plus(string)
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

    fun getBoard():Array<CharArray>{
        return board
    }

    fun submitWord(): Boolean {

        if (word.length < 4) {
            showToast("Word must be at least 4 characters long.")
            return false
        }

        if(word in ansSet){
            showToast("Word has already be entered before.")
            return false
        }

        if (!isValidWord(word)) {
            score -= 10
            showToast("Incorrect word! -10 points.")
            return false
        }

        val wordScore = calculateScore(word)
        score += wordScore
        showToast("Correct word! +${wordScore} points.")
        return true
    }

    private fun isValidWord(word: String): Boolean {
        if (!dictionary.contains(word.toLowerCase())) return false

        val uniqueLetters = word.toLowerCase().toSet()
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

        return vowelCount >= 2 && consonantCount > 0
    }

    private fun calculateScore(word: String): Int {
            ansSet.plus(word)
            score = 0
            val consonants = setOf('s', 'z', 'p', 'x', 'q')
            var double =  false
            for (letter in word.toLowerCase()) {
                if (letter in consonants) double=true
                score += if (letter in "aeiou") 5 else 1
            }
        return score
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



    fun showToast(str : String){
        Log.d("Game Debug", str)
    }


}