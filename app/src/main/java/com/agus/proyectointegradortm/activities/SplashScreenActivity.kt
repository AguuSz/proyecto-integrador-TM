package com.agus.proyectointegradortm.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import com.agus.proyectointegradortm.MyApplication
import com.agus.proyectointegradortm.R
import com.agus.proyectointegradortm.preferences.PreferencesManager


class SplashScreenActivity : AppCompatActivity() {
    private val SPLASH_TIME_OUT: Long = 1500 // 1 sec

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        // Escondemos la action bar
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        //Pasamos al login
        Handler().postDelayed({
            if (MyApplication.preferences.getRemainingLogins() == 0) {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            } else {
                MyApplication.preferences.minusOneLogin()
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            }
        }, SPLASH_TIME_OUT)

    }

}