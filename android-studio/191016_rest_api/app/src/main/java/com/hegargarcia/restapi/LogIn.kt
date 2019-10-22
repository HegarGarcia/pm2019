package com.hegargarcia.restapi

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_log_in.*
import org.json.JSONObject

class LogIn : AppCompatActivity() {

    private lateinit var preferences: SharedPreferences
    private var queue: RequestQueue? = null
    private data class User(val username: String, val password: String)

    private val hasStoredUser: Boolean
        get() = preferences.contains(USER_KEY) &&
                preferences.contains(PASSWORD_KEY)

    private val currentUser: User?
        get() = if (hasStoredUser) User(
            username = preferences.getString(USER_KEY, "")!!,
            password = preferences.getString(PASSWORD_KEY, "")!!
        ) else null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)
        supportActionBar?.hide()

        preferences = getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
        queue = Volley.newRequestQueue(this)

        if (hasStoredUser) {
            rememberCheckbox.isChecked = true
            currentUser?.also {
                emailEntry.setText(it.username)
                passwordEntry.setText(it.password)
            }
        }

        signInButton.setOnClickListener {
            sigIn()
        }

        signUpButton.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }
    }

    private fun sigIn() {
        toggleInputs()
        val requestParams = JSONObject().apply {
            put(USER_KEY, emailEntry.text.toString())
            put(PASSWORD_KEY, passwordEntry.text.toString())
        }

        val request = JsonObjectRequest(
            Request.Method.POST,
            URL,
            requestParams,
            Response.Listener<JSONObject> {
                val user = User(
                    username = it.getString(USER_KEY),
                    password = it.getString(PASSWORD_KEY)
                )

                preferences.edit().apply {
                    if (rememberCheckbox.isChecked) {
                        putString(USER_KEY, user.username)
                        putString(PASSWORD_KEY, user.password)
                    } else {
                        remove(USER_KEY)
                        remove(PASSWORD_KEY)
                    }
                    apply()
                }
                toggleInputs()
                startActivity(Intent(this, MainPage::class.java))
            },
            Response.ErrorListener {
                Alert.create(this)("Error", it.toString())
                toggleInputs()
            })

        queue?.add(request)
    }

    private fun toggleInputs() {
        emailEntry.isEnabled = !emailEntry.isEnabled
        passwordEntry.isEnabled = !passwordEntry.isEnabled
        signInButton.isEnabled = !signInButton.isEnabled
        signUpButton.isEnabled = !signUpButton.isEnabled
        progressBar.visibility =
            if (progressBar.visibility == View.GONE) View.VISIBLE else View.GONE
    }

    companion object {
        private const val PREFERENCE_NAME = "data"
        private const val USER_KEY = "user"
        private const val PASSWORD_KEY = "password"
        private const val URL = "https://orthotone-officers.000webhostapp.com/authenticate.php"
    }
}
s