package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.myapplication.database.DatabaseHelper
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.model.User

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var usernameEt : EditText
    private lateinit var passwordEt : EditText
    private lateinit var loginBtn : Button
    private lateinit var registerBtn : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        usernameEt = binding.username
        passwordEt = binding.password
        loginBtn = binding.loginButton
        registerBtn = binding.registerButton

        loginBtn.setOnClickListener {
            val username = usernameEt.text.toString()
            val password = passwordEt.text.toString()

            if(username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "You must fill all the fields!", Toast.LENGTH_SHORT).show()
            }
            else {
                val database = DatabaseHelper(this)

                val user : User? = database.getUser(username, password)

                println(user)
                if(user == null) {
                    Toast.makeText(this, "Invalid credentials!", Toast.LENGTH_SHORT).show()
                }
                else {
                    val intent = Intent(this, HomeActivity::class.java)
                    intent.putExtra("userNameExtra", user.userName)
                    intent.putExtra("userPhoneExtra", user.userPhone)

                    finishAndRemoveTask()
                    startActivity(intent)
                }
            }
        }

        registerBtn.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }



}