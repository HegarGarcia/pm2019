package com.hegargarcia.audioplayerwithcontrols

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var mediaPlayer: MediaPlayer? = null
    private var position = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mediaPlayer = MediaPlayer.create(this, R.raw.numbers)


        playButton.setOnClickListener {
            mediaPlayer?.start()
            mediaPlayer?.isLooping = cycleButton.text != getString(R.string.cycle_button)

        }

        pauseButton.setOnClickListener {
            if (mediaPlayer?.isPlaying!!) {
                position = mediaPlayer?.currentPosition!!
                mediaPlayer?.pause()
            }
        }

        continueButton.setOnClickListener {
            if (!mediaPlayer?.isPlaying!!) {
                mediaPlayer?.seekTo(position)
                mediaPlayer?.start()
            }
        }

        stopButton.setOnClickListener {
            mediaPlayer?.pause()
            position = 0
            mediaPlayer?.seekTo(position)
        }

        cycleButton.setOnClickListener {
            cycleButton.text = when(cycleButton.text) {
                getString(R.string.cycle_button) -> getString(R.string.no_cycle_button)
                getString(R.string.no_cycle_button) ->getString(R.string.cycle_button)
                else -> getString(R.string.cycle_button)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
    }
}
