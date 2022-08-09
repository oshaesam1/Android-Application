package com.ntgclarity.authenticator

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import com.ntgclarity.authenticator.api.MapsActivity
import com.ntgclarity.authenticator.api.NewsActivity

class MainActivity : AppCompatActivity() {
    val kEmail = "signature"

    var etEmail: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etEmail = findViewById<EditText>(R.id.et_email)

        loadUserEmail()

        val btnLogin = findViewById<Button>(R.id.btn_login)
        val btnRegistration = findViewById<Button>(R.id.btn_register)
        val btnSettings = findViewById<Button>(R.id.btn_settings)
        val btnMap= findViewById<Button>(R.id.btn_map)

        btnRegistration.setOnClickListener {
            startRegistration()
        }
        btnMap.setOnClickListener{
            startMap()
        }

        btnLogin.setOnClickListener {
            val email = etEmail?.text.toString()

//            shared.edit()
//                .putString(kEmail, email)
//                .apply()

            updateSignature(email)
          // startWords()
           startNews()        }

        btnSettings.setOnClickListener {
            startSettings()
        }
    }

    private fun loadUserEmail() {
        //val shared = getSharedPreferences("user.prf", MODE_PRIVATE)
        val shared = PreferenceManager.getDefaultSharedPreferences(this)
        val email = shared.getString(kEmail, null)

        etEmail?.setText(email)
    }

    private fun updateSignature(text: String) {
        val defaultPref = PreferenceManager.getDefaultSharedPreferences(this)

        defaultPref.edit()
            .putString(kEmail, text)
            .apply()
    }

    fun tryFiles() {
        val filename = "hello.txt"
        val output = openFileOutput(filename, MODE_PRIVATE)

        output.write("Hello files!".toByteArray())

        val input = openFileInput(filename)
        val lines = input.bufferedReader().lineSequence()

        Log.d("###", lines.joinToString())

        val files = fileList()

        Log.d("###", files.joinToString())
    }

    private fun startRegistration() {
        val intent = Intent(this, RegistrationActivity::class.java)

        startActivity(intent)
    }



    private fun startSettings() {
        val intent = Intent(this, SettingsActivity::class.java)

        startActivity(intent)
    }

    private fun startNews() {
        val intent = Intent(this, NewsActivity::class.java)


        startActivity(intent)
    }
    private fun startMap() {
        val intent = Intent(this, MapsActivity::class.java)
        startActivity(intent)
    }
}