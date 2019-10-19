package com.hegargarcia.restapi

import android.app.AlertDialog
import android.content.Context

class Alert {
    companion object {
        fun create(context: Context): (title: String, msg: String) -> AlertDialog {
            return { title, msg ->
                AlertDialog.Builder(context).let {
                    it.setMessage(msg)
                        .setCancelable(false)
                        .setPositiveButton("OK") { dialog, _ -> dialog.cancel() }
                        .create()
                        .apply { setTitle(title) }
                }
            }
        }
    }
}