package com.hegargarcia.suma_with_radio_buttons

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    @Suppress("UNUSED_PARAMETER")
    fun compute(v: View) {
        val firstNumber = firstEntry.text.toString().toFloat()
        val secondNumber = secondEntry.text.toString().toFloat()
        val result = when (actionGroup.checkedRadioButtonId) {
            addRadioButton.id ->
                getString(
                    R.string.result_addition,
                    firstNumber,
                    secondNumber,
                    firstNumber + secondNumber
                )

            subtractRadioButton.id ->
                getString(
                    R.string.result_subtraction,
                    firstNumber,
                    secondNumber,
                    firstNumber - secondNumber
                )
            else -> ""
        }

        resultLabel.text = result
    }
}
