package com.agus.proyectointegradortm.activities

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.agus.proyectointegradortm.R
import com.agus.proyectointegradortm.databinding.ActivityRegisterBinding
import com.agus.proyectointegradortm.utils.Validator
import com.google.android.material.snackbar.Snackbar

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnRegister.setOnClickListener {
            register(view, binding.etUsername.text, binding.etEmail.text, binding.etPassword.text)
        }
        binding.tvAlreadyAUser.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    fun register(view: ConstraintLayout, userName: Editable, email: Editable, password: Editable) {
        hideErrorMessages()
        if (email.isEmpty()) {
            showEmailError("El email no puede estar vacio")
        }
        if (userName.isEmpty()) {
            showUsernameError("El nombre no puede estar vacio")
        }
        if (password.isEmpty()) {
            showPasswordError("La password no puede estar vacia")
        }

        if (Validator.isAValidEmail(email.toString()) && Validator.isAValidPassword(password.toString())) {
            // Significa que esta listo para ser creado

            // TODO: Registrar usuario en la base de datos..

            Snackbar.make(view, "Se ha registrado correctamente!", Snackbar.LENGTH_LONG).show()

            // TODO: Importar HomeActivity aca abajo
//            val intent = Intent(this, HomeActivity::class.java)
//            startActivity(intent)
        } else {
            Snackbar.make(view, "Los datos ingresados son invalidos!", Snackbar.LENGTH_LONG).show()
        }

    }

    private fun showEmailError(message: String) {
        if (message.isNotEmpty()) {
            binding.tvEmailErrorMessage.text = message
            binding.tvEmailErrorMessage.visibility = View.VISIBLE
        }
    }

    private fun showPasswordError(message: String) {
        if (message.isNotEmpty()) {
            binding.tvPasswordErrorMessage.text = message
            binding.tvPasswordErrorMessage.visibility = View.VISIBLE
        }
    }

    private fun showUsernameError(message: String) {
        if (message.isNotEmpty()) {
            binding.tvUsernameErrorMessage.text = message
            binding.tvUsernameErrorMessage.visibility = View.VISIBLE
        }
    }

    fun hideErrorMessages() {
        binding.tvUsernameErrorMessage.visibility = View.GONE
        binding.tvEmailErrorMessage.visibility = View.GONE
        binding.tvPasswordErrorMessage.visibility = View.GONE
    }
}