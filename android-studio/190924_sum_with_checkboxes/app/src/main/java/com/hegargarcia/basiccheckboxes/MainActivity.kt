package com.hegargarcia.basiccheckboxes

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
        val firstValue = firstEntry.text.toString().toFloat()
        val secondValue = secondEntry.text.toString().toFloat()
        var result = ""

        if (addCheckbox.isChecked) {
            result = getString(
                R.string.result_add,
                firstValue,
                secondValue,
                firstValue + secondValue
            )
        } else if (subtractCheckbox.isChecked) {
            result = getString(
                R.string.result_subtract,
                firstValue,
                secondValue,
                firstValue - secondValue
            )
        }

        resultText.text = result
    }
}
