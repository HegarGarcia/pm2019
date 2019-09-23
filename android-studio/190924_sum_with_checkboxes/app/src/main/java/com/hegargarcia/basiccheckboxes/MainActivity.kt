package com.hegargarcia.basiccheckboxes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        actionButton.setOnClickListener {
            val firstValue = firstEntry.text.toString().toInt()
            val secondValue = secondEntry.text.toString().toInt()
            val result = if (addCheckbox.isChecked) getString(
                R.string.result_add,
                firstValue,
                secondValue,
                firstValue + secondValue
            ) else if (subtractCheckbox.isChecked) getString(
                R.string.result_add,
                firstValue,
                secondValue,
                firstValue - secondValue
            ) else ""

            resultText.text = result
        }
    }
}
