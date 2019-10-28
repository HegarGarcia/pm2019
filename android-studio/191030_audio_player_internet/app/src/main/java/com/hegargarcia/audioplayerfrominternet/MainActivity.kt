package com.hegargarcia.audioplayerfrominternet

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mediaPlayer = MediaPlayer()

        playButton.setOnClickListener {
            mediaPlayer.reset()
            try {
                mediaPlayer.apply {
                    setDataSource("https://firebasestorage.googleapis.com/v0/b/bebin-baacpa.appspot.com/o/iphone-notificacion.mp3?alt=media&token=cf24a16d-4a9b-4a1c-a370-10040f08a181")
                    prepare()
                    start()

                }
            } catch (e: IOException) {
                Toast.makeText(this, R.string.error, Toast.LENGTH_LONG).show()
            }
        }
    }
}
