package com.hegargarcia.secondactivitywithpassword

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loginButton.setOnClickListener {
            val password = passwordEntry.text.toString()

            if (password != "abc123") {
                return@setOnClickListener
            }

            val intent = Intent(this, Welcome::class.java)
            startActivity(intent)
        }
    }
}
