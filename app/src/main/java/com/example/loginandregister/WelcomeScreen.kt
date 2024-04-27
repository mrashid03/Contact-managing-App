package com.example.loginandregister

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import com.example.loginandregister.databinding.ActivityMainBinding
import com.example.loginandregister.databinding.ActivityWelcomeScreenBinding

class WelcomeScreen : AppCompatActivity() {
    private lateinit var binding : ActivityWelcomeScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, LogInActivity::class.java))
            finish()
        },2000)

        val welcomeText = "Welcome"
        val spannableString = SpannableString(welcomeText)
        spannableString.setSpan(ForegroundColorSpan(Color.parseColor("#FF0000")),0,5,0)
        spannableString.setSpan(ForegroundColorSpan(Color.parseColor("#000000")),5,welcomeText.length,0)

        binding.welcomeText.text = spannableString
    }
}