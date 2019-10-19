package com.hegargarcia.dailyactivities

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.io.*
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        saveButton.setOnClickListener { saveActivities() }
        loadButton.setOnClickListener { loadActivities() }
    }

    private fun getFilename(): String {
        return dateEntry.text.toString().replace('/', '-')
    }

    private fun saveActivities() {
        val filename = getFilename()
        val todoText = todoEntry.text.toString()

        try {
            val sdCard = this.getExternalFilesDir(null)
            val file = File(sdCard!!.absolutePath, filename)
            val writer = OutputStreamWriter(FileOutputStream(file))
            writer.write(todoText)
            writer.flush()
            writer.close()
        } catch (e: IOException) {
            Log.d("ERROR", e.toString())
        }

        Toast.makeText(this, R.string.successful_save, Toast.LENGTH_LONG).show()
        dateEntry.text.clear()
        todoEntry.text.clear()
    }

    private fun loadActivities() {
        val filename = getFilename()
        val todoText = loadDataFromFile(filename)
        todoEntry.setText(todoText)
    }

    private fun loadDataFromFile(filename: String): String {
        val todoText = StringBuilder()
        if (fileList().contains(filename)) {
            try {
                val sdCard = this.getExternalFilesDir(null)
                val file = FileInputStream(File(sdCard!!.absolutePath, filename))
                val buffer = BufferedReader(InputStreamReader(file))
                var line = buffer.readLine()
                while (!line.isNullOrEmpty()) {
                    todoText.append(line)
                    todoText.append("\n")
                    line = buffer.readLine()
                }

                buffer.close()
                file.close()

            } catch (e: IOException) {
                Log.e("ERROR", e.toString())
            }
        } else {
            Toast.makeText(this, R.string.no_data, Toast.LENGTH_LONG).show()
        }
        return todoText.toString()
    }
}
