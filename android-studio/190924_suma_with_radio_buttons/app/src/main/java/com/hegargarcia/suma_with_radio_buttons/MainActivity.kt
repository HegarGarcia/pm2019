package com.hegargarcia.suma_with_radio_buttons

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        computeButton.setOnClickListener {
            val firstNumber = firstEntry.text.toString().toInt()
            val secondNumber = secondEntry.text.toString().toInt()

            when (actionGroup.checkedRadioButtonId) {
                addRadioButton.id -> {
                    val result = firstNumber + secondNumber
                    resultLabel.text =
                        getString(R.string.result_addition, firstNumber, secondNumber, result)
                }
                subtractRadioButton.id -> {
                    val result = firstNumber - secondNumber
                    resultLabel.text =
                        getString(R.string.result_subtraction, firstNumber, secondNumber, result)
                }
            }
        }
    }
}
