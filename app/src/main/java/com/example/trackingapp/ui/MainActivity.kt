package com.example.trackingapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.trackingapp.R
import com.example.trackingapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}