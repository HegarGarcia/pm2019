package com.hegargarcia.basicspinner

import android.os.Bundle
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
            android.R.layout.simple_spinner_item,
            itemList
        )
        actionSelector.adapter = adapter

        actionButton.setOnClickListener {
            val firstValue = firstEntry.text.toString().toInt()
            val secondValue = secondEntry.text.toString().toInt()
            val result = when(actionSelector.selectedItem.toString()) {
                itemList[0] -> getString(R.string.result_add, firstValue, secondValue, firstValue + secondValue)
                itemList[1] -> getString(R.string.result_subtract, firstValue, secondValue, firstValue - secondValue)
                else -> ""
            }

            resultText.text = result
        }
    }
}
