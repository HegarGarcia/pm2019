package com.hegargarcia.sqlite

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Database(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DB_NAME, factory, DB_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE $TABLE_NAME ($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, $COLUMN_DESCRIPTION TEXT, $COLUMN_PRICE REAL)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {}

    fun addItem(item: Item): Int {
        val values = getContentValuesFromItem(item)
        values.remove(COLUMN_ID)
        val db = this.writableDatabase
        val itemId = db.insert(TABLE_NAME, null, values).toInt()
        db.close()
        return itemId
    }

    fun getItemById(id: Int): Cursor {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_NAME WHERE $COLUMN_ID = $id", null)
    }

    fun getItemByDescription(description: String): Cursor {
        val db = this.readableDatabase
        return db.rawQuery(
            "SELECT * FROM $TABLE_NAME WHERE $COLUMN_DESCRIPTION = \"$description\"",
            null
        )
    }

    fun deleteById(id: Int): Int {
        val db = this.writableDatabase
        return db.delete(TABLE_NAME, "$COLUMN_ID = $id", null)
    }

    fun updateItem(id: Int, item: Item): Int {
        val values = getContentValuesFromItem(item)
        val db = this.writableDatabase
        return db.update(TABLE_NAME, values, "$COLUMN_ID = $id", null)
    }

    private fun getContentValuesFromItem(item: Item): ContentValues {
        val values = ContentValues()
        values.put(COLUMN_ID, item.id)
        values.put(COLUMN_DESCRIPTION, item.description)
        values.put(COLUMN_PRICE, item.price)
        return values
    }

    companion object {
        private const val DB_VERSION = 1
        private const val DB_NAME = "PM2019"
        const val TABLE_NAME = "items"
        const val COLUMN_ID = "id"
        const val COLUMN_DESCRIPTION = "description"
        const val COLUMN_PRICE = "price"
    }
}
