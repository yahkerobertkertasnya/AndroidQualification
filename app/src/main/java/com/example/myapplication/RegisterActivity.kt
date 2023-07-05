package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.myapplication.database.DatabaseHelper
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.databinding.ActivityRegisterBinding
import com.example.myapplication.model.User

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRegisterBinding
    private lateinit var usernameEt : EditText
    private lateinit var phoneEt : EditText
    private lateinit var passwordEt : EditText
    private lateinit var passwordConfirmEt : EditText
    private lateinit var loginBtn : Button
    private lateinit var registerBtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        usernameEt = binding.registerUsername
        phoneEt = binding.registerPhone
        passwordEt = binding.registerPassword
        passwordConfirmEt = binding.registerPasswordConfirm
        loginBtn = binding.loginRedirectButton
        registerBtn = binding.registerRedirectButton

        loginBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        registerBtn.setOnClickListener {
            val username = usernameEt.text.toString()
            val phone = phoneEt.text.toString()
            val password = passwordEt.text.toString()
            val passwordConfirm = passwordConfirmEt.text.toString()

            if(username.isEmpty() || password.isEmpty() || phone.isEmpty() || passwordConfirm.isEmpty()) {
                Toast.makeText(this, "You must fill all the fields!", Toast.LENGTH_SHORT).show()
            }
            else if(password != passwordConfirm){
                Toast.makeText(this, "Password does not match!", Toast.LENGTH_SHORT).show()
            }
            else {
                val database = DatabaseHelper(this)

                val user = User(null, username, phone, password)
                database.insertUser(user)

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}