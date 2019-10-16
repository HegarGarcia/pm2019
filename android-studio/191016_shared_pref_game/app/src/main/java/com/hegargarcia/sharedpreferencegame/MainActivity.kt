package com.hegargarcia.sharedpreferencegame

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var preferences: SharedPreferences
    private var magicNumber = Random.nextInt(1, 50)
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        preferences = getSharedPreferences("game", Context.MODE_PRIVATE)
        score = preferences.getInt("score", 0)

        updateScore()
        guessButton.setOnClickListener { guess() }
    }

    private fun saveScore() {
        val editor = preferences.edit()
        editor.putInt("score", score)
        editor.apply()
        updateScore()
    }

    private fun updateScore() {
        scoreLabel.text = getString(R.string.score, score)
    }

    private fun guess() {
        when (guessEntry.text.toString().toInt()) {
            magicNumber -> {
                magicNumber = Random.nextInt(1, 50)
                score += 1
                guessEntry.text.clear()
                saveScore()
                Toast.makeText(this, getString(R.string.right), Toast.LENGTH_LONG).show()
            }
            else -> Toast.makeText(this, getString(R.string.wrong), Toast.LENGTH_LONG).show()
        }
    }
}
