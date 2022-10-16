package com.agus.proyectointegradortm.utils

import android.text.TextUtils
import java.util.regex.Pattern

class Validator {
    companion object {
        fun isAValidEmail(email: String): Boolean {
            if (TextUtils.isEmpty(email)) return false

            return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }

        fun isAValidPassword(password: String): Boolean {
            val pattern: Pattern = Pattern.compile("^" +
                    "(?=.*[@#$%^&+=])" +     // at least 1 special character
                    "(?=\\S+$)" +            // no white spaces
                    ".{4,}" +                // at least 4 characters
                    "$");

            return (pattern.matcher(password).matches())
        }
    }
}