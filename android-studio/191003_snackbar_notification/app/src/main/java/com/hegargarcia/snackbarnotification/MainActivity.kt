package com.hegargarcia.snackbarnotification

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val magicNumber = (Math.random() * 100_001).toInt()
        Snackbar.make(
            root_layout,
            getString(R.string.toast_string, magicNumber),
            Snackbar.LENGTH_SHORT
        ).show()

        actionButton.setOnClickListener {
            val selectedNumber = answerEntry.text.toString().toInt()
            if (magicNumber == selectedNumber) {
                Snackbar.make(
                    root_layout,
                    R.string.correct_answer,
                    Snackbar.LENGTH_SHORT
                ).show()
            } else {
                Snackbar.make(
                    root_layout,
                    R.string.incorrect_answer,
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
    }
}
