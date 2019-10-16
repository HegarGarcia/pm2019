package com.hegargarcia.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val preferences = getSharedPreferences("data", Context.MODE_PRIVATE)
        emailEntry.setText(preferences.getString("email", ""))

        actionButton.setOnClickListener {
            saveEmail(preferences)
        }
    }

    private fun saveEmail(preferences: SharedPreferences) {
        val editor = preferences.edit()
        val email = emailEntry.text.toString()
        editor.putString("email", email)
        editor.apply()
        finish()
    }
}
