package com.project.aplikasiabsensi.view

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.android.volley.Response
import com.android.volley.toolbox.Volley
import com.project.aplikasiabsensi.databinding.ActivityLoginBinding
import com.project.aplikasiabsensi.model.LoginRequest
import org.json.JSONException
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {

    //implement View binding
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sp : SharedPreferences = getSharedPreferences("login", MODE_PRIVATE)

        if (sp.getBoolean("logged", false)) {
            val intent = Intent(this@LoginActivity, DashboardActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogin.setOnClickListener {

            val username = binding.idUsername.text.toString()
            val password = binding.idPassword.text.toString()
            showLoading(true)

            val responseListener =
                Response.Listener<String> { response ->
                    try {
                        val jsonResponse = JSONObject(response)
                        val success = jsonResponse.getBoolean("success")
                        if (success) {
                            binding.btnLogin.isClickable = false
                            //getting value from database mysql
                            val id = jsonResponse.getString("id_akun")
                            val namaAkun = jsonResponse.getString("nama_akun")
                            val tUsername = jsonResponse.getString("username")
                            val tPassword = jsonResponse.getString("password")
                            val role = jsonResponse.getString("role")
                            val kelasMapel = jsonResponse.getString("kelas_mapel")

                            val intent = Intent(this@LoginActivity, DashboardActivity::class.java)
                            val pref = applicationContext.getSharedPreferences("MyPref", MODE_PRIVATE)
                            val editor = pref.edit()
                            //saving data to shared pref
                            editor.putString("id_akun", id)
                            editor.putString("nama_akun", namaAkun)
                            editor.putString("username", tUsername)
                            editor.putString("password", tPassword)
                            editor.putString("role", role)
                            editor.putString("kelas_mapel", kelasMapel)
                            editor.apply() // commit changes
                            sp.edit().putBoolean("logged",true).apply()
                            this@LoginActivity.startActivity(intent)
                            finish()
                            showLoading(false)
                        } else {
                            val builder = AlertDialog.Builder(this@LoginActivity)
                            builder.setMessage("username atau password tidak terdaftar")
                                .setNegativeButton("Ok", null)
                                .create()
                                .show()
                            showLoading(false)
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            val loginRequest = LoginRequest(username, password, responseListener)
            val queue = Volley.newRequestQueue(this@LoginActivity)
            queue.add(loginRequest)

        }


    }

    override fun onBackPressed() {
        this@LoginActivity.moveTaskToBack(true)
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.loadingProgress.visibility = View.VISIBLE
        } else {
            binding.loadingProgress.visibility = View.INVISIBLE
        }
    }

}