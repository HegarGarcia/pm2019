package com.hegargarcia.audioplayer

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        catButton.setOnClickListener {
            playAudio(R.raw.cat)
        }

        lionButton.setOnClickListener {
            playAudio(R.raw.lion)
        }
    }

    private fun playAudio(audio: Int) {
        MediaPlayer.create(this, audio).start()
    }
}
