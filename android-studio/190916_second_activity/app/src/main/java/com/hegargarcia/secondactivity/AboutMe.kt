package com.hegargarcia.secondactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_about_me.*
import kotlinx.android.synthetic.main.activity_main.*

class AboutMe : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_me)

        closeButton.setOnClickListener {
            finish()
        }
    }
}
