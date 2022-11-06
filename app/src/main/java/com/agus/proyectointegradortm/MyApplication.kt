package com.agus.proyectointegradortm

import android.app.Application
import com.agus.proyectointegradortm.preferences.PreferencesManager

class MyApplication: Application() {
    companion object {
        lateinit var preferences: PreferencesManager
    }

    override fun onCreate() {
        super.onCreate()
        preferences = PreferencesManager(applicationContext)
    }
}