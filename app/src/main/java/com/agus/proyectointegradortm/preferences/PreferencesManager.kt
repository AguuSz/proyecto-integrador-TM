package com.agus.proyectointegradortm.preferences

import android.content.Context
import android.content.SharedPreferences

class PreferencesManager(context: Context) {
    private val PREFS_NAME = "com.agus.proyectointegradortm.sharedPreferences"

    val preferences : SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    private val KEY_USER_NAME = "USER_NAME"
    private val KEY_USER_EMAIL = "USER_EMAIL"
    private val KEY_REMAINING_LOGINS = "REMAINING_LOGINS"

    fun setUserName(userName: String) {
        preferences.edit().putString(KEY_USER_NAME, userName).apply()
    }

    fun getUserName(): String {
        return preferences.getString(KEY_USER_NAME, "") ?: ""
    }

    fun setUserEmail(email: String) {
        preferences.edit().putString(KEY_USER_EMAIL, email).apply()
    }

    fun getUserEmail(): String {
        return preferences.getString(KEY_USER_EMAIL, "") ?: ""
    }

    fun setRemainingLogins(remainingLogins: Int) {
        preferences.edit().putInt(KEY_REMAINING_LOGINS, remainingLogins).apply()
    }

    fun minusOneLogin() {
        preferences.edit().putInt(KEY_REMAINING_LOGINS, getRemainingLogins() - 1).apply()
    }

    fun getRemainingLogins(): Int {
        return preferences.getInt(KEY_REMAINING_LOGINS, 0)
    }
}