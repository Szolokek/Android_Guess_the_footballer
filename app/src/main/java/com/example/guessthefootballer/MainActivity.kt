package com.example.guessthefootballer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.guessthefootballer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnPlay.setOnClickListener{
            startActivity(Intent(this, GameActivity::class.java))
        }

        binding.btnQuit.setOnClickListener { finish() }

         binding.btnPlay.animate().apply {
             duration = 1000
             alpha(1f)
         }.start()

        binding.btnQuit.animate().apply {
            duration = 1000
            alpha(1f)
        }.start()
    }
}