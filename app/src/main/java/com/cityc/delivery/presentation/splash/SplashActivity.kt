package com.cityc.delivery.presentation.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cityc.delivery.presentation.main.MainActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(MainActivity.getStartIntent(this, null))
    }
}