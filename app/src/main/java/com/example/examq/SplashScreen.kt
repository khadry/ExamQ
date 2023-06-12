package com.example.examq

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity


class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed(
            {
                val pindahSplashScreen = Intent(this, MenuDepan::class.java)
                startActivity(pindahSplashScreen)
            }, 3000
        )
    }
}

