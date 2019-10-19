package com.hegargarcia.dataintextfile

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {

    private val fileName = "notes.txt"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (fileList().contains(fileName)) {
            try {
                val file = InputStreamReader(openFileInput(fileName))
                val buffer = BufferedReader(file)
                var line = buffer.readLine()

                val infoText = StringBuilder()

                while (!line.isNullOrEmpty()) {
                    infoText.append(line)
                    infoText.append("\n")
                    line = buffer.readLine()
                }

                buffer.close()
                file.close()

                textEntry.setText(infoText)
            } catch (e: IOException) {
                Log.d("IO Error", e.toString())
            }
        }

        actionButton.setOnClickListener { saveText() }
    }

    private fun saveText() {
        try {
            val file = OutputStreamWriter(openFileOutput(fileName, Activity.MODE_PRIVATE))
            val infoText = textEntry.text.toString()
            file.write(infoText)
            file.flush()
            file.close()
        } catch (e: IOException) {
            Log.d("IO Error", e.toString())
        }

        Toast.makeText(this, getString(R.string.success), Toast.LENGTH_SHORT).show()

        finish()
    }
}
