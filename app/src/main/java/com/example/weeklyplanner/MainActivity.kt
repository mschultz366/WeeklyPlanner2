package com.example.weeklyplanner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    enum class LoginValidation constructor(val returnCode: Int) {
        usernameFailure(1),
        passwordFailure(2),
        loginSuccess(0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val user = findViewById<EditText>(R.id.hUsername)
        val pass = findViewById<EditText>(R.id.hPassword)
        val button = findViewById<Button>(R.id.hLoginButton)

        button.setOnClickListener {

            when(checkLogin(user.text.toString(), pass.text.toString())) {
                LoginValidation.usernameFailure -> {
                    Toast.makeText(applicationContext, getString(R.string.username_error), Toast.LENGTH_LONG).show()
                    user.requestFocus()
                }
                LoginValidation.passwordFailure -> {
                    Toast.makeText(applicationContext, getString(R.string.password_error), Toast.LENGTH_LONG).show()
                    pass.requestFocus()
                }
                LoginValidation.loginSuccess -> {
                    startActivity(Intent(this@MainActivity, MainClassList::class.java))
                    Toast.makeText(applicationContext, "Success!!", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun checkLogin(u: String, p: String) : LoginValidation {

        val validUser = "mckenna"
        val validPass = "password"

        if(u != validUser) {
            return LoginValidation.usernameFailure
        }
        return if(p != validPass) {
            LoginValidation.passwordFailure
        } else LoginValidation.loginSuccess
    }
}