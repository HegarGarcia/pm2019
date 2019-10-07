package com.hegargarcia.listview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val countryAdapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_list_item_1,
            resources.getStringArray(R.array.countries)
        )
        countryList.adapter = countryAdapter

        countryList.setOnItemClickListener { _, _, i, _ ->
            val population = resources.getStringArray(R.array.populations)[i].toInt()
            populationLabel.text = getString(R.string.populationText, population)
        }
    }
}
