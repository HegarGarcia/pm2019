package com.hegargarcia.audioplayerintegrated

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        playButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            val data = Uri.parse("https://firebasestorage.googleapis.com/v0/b/bebin-baacpa.appspot.com/o/iphone-notificacion.mp3?alt=media&token=cf24a16d-4a9b-4a1c-a370-10040f08a181")
            intent.setDataAndType(data, "audio/mp3")
            startActivity(intent)
        }
    }
}
