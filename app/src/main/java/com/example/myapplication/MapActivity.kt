package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityMapBinding
import com.example.myapplication.map.MapFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MapActivity : AppCompatActivity() {

    private lateinit var backButton: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        backButton = findViewById(R.id.map_button_back_redirect)

        backButton.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)

            startActivity(intent)
        }



        supportFragmentManager.beginTransaction().replace(R.id.map_containerz, MapFragment()).commit()
    }
}