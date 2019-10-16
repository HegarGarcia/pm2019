package com.hegargarcia.secondactivitywithparameters

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        actionButton.setOnClickListener {
            val intent = Intent(this, Website::class.java)

            val url = websiteEntry.text.toString()
            intent.putExtra("url", url)

            startActivity(intent)
        }
    }
}
