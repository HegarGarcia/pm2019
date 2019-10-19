package com.hegargarcia.sqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Error

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        saveButton.setOnClickListener { addItem() }
        deleteButton.setOnClickListener { deleteItem() }
        searchByIdButton.setOnClickListener { getItemById() }
        searchByDescriptionButton.setOnClickListener { getItemByDescription() }
        updateButton.setOnClickListener { updateItem() }
    }

    private fun addItem() {
        val description = descriptionEntry.text.toString()
        val price = priceEntry.text.toString().toFloat()
        val item = Item(0, description, price)
        val model = Database(this, null)
        idEntry.setText(model.addItem(item).toString())

        Toast.makeText(this, R.string.add_msg, Toast.LENGTH_LONG).show()
    }

    private fun deleteItem() {
        val id = idEntry.text.toString().toInt()
        try {
            Database(this, null).apply {
                deleteById(id)
            }
        } catch (e: Error) {
            Log.e("ERROR", e.toString())
        }
    }

    private fun getItemById() {
        val id = idEntry.text.toString().toInt()
        try {
            val model = Database(this, null)
            val cursor = model.getItemById(id)

            if (cursor.count == 0) {
                Toast.makeText(this, R.string.not_fount, Toast.LENGTH_LONG).show()
            } else {
                cursor.moveToFirst()
                descriptionEntry.setText(cursor.getString(1))
                priceEntry.setText(cursor.getString(2))
                cursor.close()
            }
        } catch (e: Error) {
            Log.e("ERROR", e.toString())
        }
    }

    private fun getItemByDescription() {
        val description = descriptionEntry.text.toString()
        try {
            val model = Database(this, null)
            val cursor = model.getItemByDescription(description)

            if (cursor.count == 0) {
                Toast.makeText(this, R.string.not_fount, Toast.LENGTH_LONG).show()
            } else {
                cursor.moveToFirst()
                idEntry.setText(cursor.getString(0))
                priceEntry.setText(cursor.getString(2))
                cursor.close()
            }
        } catch (e: Error) {
            Log.e("ERROR", e.toString())
        }
    }

    private  fun updateItem() {
        val id = idEntry.text.toString().toInt()
        val description = descriptionEntry.text.toString()
        val price = priceEntry.text.toString().toFloat()
        val item = Item(id, description, price)

        Database(this, null).apply {
            updateItem(id, item)
        }
    }
}
