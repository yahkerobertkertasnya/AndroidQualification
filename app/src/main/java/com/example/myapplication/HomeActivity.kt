package com.example.myapplication

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.adapter.UserAdapter
import com.example.myapplication.database.DatabaseHelper
import com.example.myapplication.databinding.ActivityHomeBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var userRecycle: RecyclerView
    private lateinit var userAdapter: UserAdapter
    private lateinit var smsButton: Button
    private lateinit var mapButton: FloatingActionButton
    private lateinit var smsText: EditText
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        databaseHelper = DatabaseHelper(this)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userArrays = databaseHelper.getUsers()

        println(userArrays)
        userRecycle = binding.userRecycle

        userAdapter = UserAdapter(userArrays)

        userRecycle.layoutManager = LinearLayoutManager(this)
        userRecycle.adapter = userAdapter

        smsButton = binding.phoneButton
        mapButton = binding.mapButtonRedirect

        smsText = binding.phoneInput

        smsButton.setOnClickListener {
            smsHandler()
        }

        mapButton.setOnClickListener {
            val intent = Intent(this, MapActivity::class.java)

            startActivity(intent)
        }

    }

    private fun smsHandler(){
        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.SEND_SMS), 4321)
        }
        else {
            val smsManager = SmsManager.getDefault()
            smsManager.sendTextMessage(smsText.text.toString(), null,"hello its me", null, null)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 4321 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            val smsManager = SmsManager.getDefault()
            smsManager.sendTextMessage(smsText.text.toString(), null, "hello its me", null, null)
        }
    }
}