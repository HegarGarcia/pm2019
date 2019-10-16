package com.hegargarcia.sharedpreferencesagenda

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val preferences = getSharedPreferences("agenda", Context.MODE_PRIVATE)

        saveButton.setOnClickListener { saveData(preferences) }
        loadButton.setOnClickListener { loadData(preferences) }
    }

    private fun saveData(preferences: SharedPreferences) {
        val editor = preferences.edit()
        val name = nameEntry.text.toString()
        val info = infoEntry.text.toString()

        editor.putString(name, info)
        editor.apply()

        Toast.makeText(this, getString(R.string.successful_load), Toast.LENGTH_LONG).show()
    }

    private fun loadData(preferences: SharedPreferences) {
        val name = nameEntry.text.toString()

        val info = preferences.getString(name, "")

        if (info?.isEmpty()!!) {
            Toast.makeText(this, getString(R.string.no_data), Toast.LENGTH_LONG).show()
        } else {
            infoEntry.setText(info)
        }
    }
}
