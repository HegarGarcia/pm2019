package com.hegargarcia.toastnotification

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.widget.Toast.makeText
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val magicNumber = (Math.random() * 100_001).toInt()
        makeText(
            this,
            getString(R.string.toast_string, magicNumber),
            Toast.LENGTH_SHORT
        ).show()

        actionButton.setOnClickListener {
            val selectedNumber = answerEntry.text.toString().toInt()
            if (magicNumber == selectedNumber) {
                makeText(this, R.string.correct_answer, Toast.LENGTH_LONG).show()
            } else {
                makeText(this, R.string.incorrect_answer, Toast.LENGTH_LONG).show()
            }
        }
    }
}
