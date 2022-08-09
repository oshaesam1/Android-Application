package com.ntgclarity.authenticator

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.ntgclarity.authenticator.database.User
import com.ntgclarity.authenticator.database.UsersDatabase

class RegistrationActivity : AppCompatActivity() {
    var etName: EditText? = null
    var etEmail: EditText? = null
    var etPassword: EditText? = null

    var database: UsersDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        etName = findViewById<EditText>(R.id.et_name)
        etEmail = findViewById<EditText>(R.id.et_email)
        etPassword = findViewById<EditText>(R.id.et_password)

        val btnSave = findViewById<Button>(R.id.btn_save)

        database = Room.databaseBuilder(this, UsersDatabase::class.java, "users.db")
            .allowMainThreadQueries()
            .build()

        btnSave.setOnClickListener {
            saveToDatabase()

            logUsers()

            finish()
        }
    }

    fun logUsers() {
        val users = database?.userDao()?.getAllUsers()

        Log.d("###", users.toString())
    }

    fun saveToDatabase() {
        val name = etName?.text.toString()
        val email = etEmail?.text.toString()
        val password = etPassword?.text.toString()

        val user = User(name = name, email = email, password = password)

        database?.userDao()?.insertUser(user)
    }
}