package com.driverco.todoList1

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity


class SplashActivity : AppCompatActivity() {
    private val splashDur = 2000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT*/
        setContentView(R.layout.activity_splash)
        Looper.myLooper()?.let {
            Handler(it).postDelayed({
                val intent = Intent(this, TodoActivity::class.java)
                startActivity(intent)
                finish()
            }, splashDur.toLong())
        }
    }

/*        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_splash_activity)
        Handler().postDelayed(Runnable {
            val intent = Intent(this@splash_activity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, SPLASH_DUR)*/
}