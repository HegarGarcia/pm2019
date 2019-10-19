package com.hegargarcia.restapi

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.json.JSONObject

class SignUp : AppCompatActivity() {

    private var queue: RequestQueue? = null

    companion object {
        private const val FULL_NAME_KEY = "name"
        private const val USER_KEY = "user"
        private const val PASSWORD_KEY = "password"
        private const val URL = "https://orthotone-officers.000webhostapp.com/add_user.php"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        supportActionBar?.hide()

        queue = Volley.newRequestQueue(this)
        signUpButton.setOnClickListener {
            signUp()
        }
    }

    private fun signUp() {
        toggleInputs()
        val requestParams = JSONObject().apply {
            put(USER_KEY, usernamePrompt.text.toString())
            put(PASSWORD_KEY, passwordPrompt.text.toString())
            put(FULL_NAME_KEY, fullNamePrompt.text.toString())
        }

        val request = JsonObjectRequest(
            Request.Method.POST,
            URL,
            requestParams,
            Response.Listener<JSONObject> {
                toggleInputs()
                if (it.getString("error").isNotEmpty()) {
                    Alert.create(this)("User", "Username already in use!")
                } else {
                    finish()
                }
            },
            Response.ErrorListener {
                Alert.create(this)("Error", it.toString())
                toggleInputs()
            })

        queue?.add(request)
    }

    private fun toggleInputs() {
        fullNamePrompt.isEnabled = !fullNamePrompt.isEnabled
        usernamePrompt.isEnabled = !usernamePrompt.isEnabled
        passwordPrompt.isEnabled = !passwordPrompt.isEnabled
        signUpButton.isEnabled = !signUpButton.isEnabled
    }
}
