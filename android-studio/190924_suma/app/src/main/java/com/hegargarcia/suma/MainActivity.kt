package com.hegargarcia.suma

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            val firstValue = firstValue.text.toString().toInt()
            val secondValue = secondValue.text.toString().toInt()
            val sum = firstValue + secondValue
            resultLabel.text = getString(R.string.result, sum)
        }
    }
}
