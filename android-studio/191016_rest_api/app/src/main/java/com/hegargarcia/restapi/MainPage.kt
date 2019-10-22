package com.hegargarcia.restapi

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main_page.*
import org.json.JSONArray

class MainPage : AppCompatActivity() {

    private var queue: RequestQueue? = null
    private var users: MutableList<User> = mutableListOf()

    private data class User(val username: String, val password: String, val name: String)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)

        queue = Volley.newRequestQueue(this)

        val request = JsonArrayRequest(
            Request.Method.POST,
            URL,
            Response.Listener<JSONArray> {
                for (i in 0 until it.length()) {
                    val user = it.getJSONObject(i).let { user ->
                        User(
                            name = user.getString("name"),
                            username = user.getString("user"),
                            password = user.getString("password")
                        )
                    }
                    users.add(i, user)
                }

                val adapter = ArrayAdapter<String>(
                    this,
                    android.R.layout.simple_list_item_1,
                    users.map { user -> user.name })
                usersList.adapter = adapter
            },
            Response.ErrorListener {
                Alert.create(this)("Error", it.toString())
            })

        usersList.setOnItemClickListener { _, _, position, _ ->
            val user = users[position]
            Alert.create(this)(
                user.name, """
                Username: ${user.username}
                Password: ${user.password}
            """.trimIndent()
            )
        }

        queue?.add(request)
    }

    companion object {
        private const val URL = "https://orthotone-officers.000webhostapp.com/get_users.php"
    }
}
