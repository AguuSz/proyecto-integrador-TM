package com.agus.proyectointegradortm

import android.app.Application
import android.content.Context
import com.agus.proyectointegradortm.models.Product
import com.agus.proyectointegradortm.preferences.PreferencesManager

class MyApplication : Application() {
    companion object {
        lateinit var preferences: PreferencesManager
        lateinit var appContext: Context
        var cart = mutableListOf<Product>()
        var history = mutableListOf<Product>()
    }

    override fun onCreate() {
        super.onCreate()
        preferences = PreferencesManager(applicationContext)
        appContext = applicationContext
    }
}