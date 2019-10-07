package com.hegargarcia.basicspinner

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val itemList = resources.getStringArray(R.array.actions)
        val adapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            itemList
        )
        actionSelector.adapter = adapter
    }

    @Suppress("UNUSED_PARAMETER")
    fun compute(v: View) {
        val itemList = resources.getStringArray(R.array.actions)
        val firstValue: Float = if (firstEntry.text.toString().isNotEmpty()) firstEntry.text.toString().toFloat() else 0.0f
        val secondValue = if (secondEntry.text.toString().isNotEmpty()) secondEntry.text.toString().toFloat() else 0.0f
        val result = when(actionSelector.selectedItem.toString()) {
            itemList[0] -> getString(R.string.result_add, firstValue, secondValue, firstValue + secondValue)
            itemList[1] -> getString(R.string.result_subtract, firstValue, secondValue, firstValue - secondValue)
            itemList[2] -> getString(R.string.result_multiplication, firstValue, secondValue, firstValue * secondValue)
            itemList[3] -> getString(R.string.result_division, firstValue, secondValue, firstValue / secondValue)
            else -> ""
        }

        resultText.text = result
    }
}
