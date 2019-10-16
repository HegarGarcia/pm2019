package com.hegargarcia.secondactivitywithparameters

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_website.*
import android.webkit.WebViewClient
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T



class Website : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_website)

        val bundle = intent.extras

        val url = bundle?.getString("url")
        web.webViewClient = WebViewClient()
        web.loadUrl(url)

        closeButton.setOnClickListener {
            finish()
        }
    }
}
