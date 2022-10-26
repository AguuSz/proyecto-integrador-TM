package com.agus.proyectointegradortm.activities

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.agus.proyectointegradortm.R
import com.agus.proyectointegradortm.databinding.ActivityLoginBinding
import com.agus.proyectointegradortm.utils.Validator
import com.google.android.material.snackbar.Snackbar

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // handleClickListeners
        binding.btnLogin.setOnClickListener {
            login(view, binding.etEmail.text, binding.etPassword.text)
        }
        binding.tvForgotPassword.setOnClickListener {
            goTo(ForgotPasswordScreen::class.java)
        }
    }

    private fun login (view: ConstraintLayout, email: Editable, password: Editable) {
        if (email.isEmpty() || password.isEmpty()) {
            Snackbar.make(view, "Los datos no pueden estar vacios", Snackbar.LENGTH_LONG).show()
            return
        }

        val correctEmail = "emailTest@asd.com"
        val correctPassword = "asdasd123"

        if (!Validator.isAValidEmail(email.toString())) {
            Snackbar.make(view, "Email invalido", Snackbar.LENGTH_LONG).show()
        }

        if (email.toString() == correctEmail && password.toString() == correctPassword) {
            goTo(HomeActivity::class.java)
        } else {
            Snackbar.make(view, "No existe una cuenta con esos datos", Snackbar.LENGTH_LONG).show()
        }
    }

    fun goTo(activity: Class<*>) {
        val intent = Intent(this, activity)
        startActivity(intent)
    }
}