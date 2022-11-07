package com.agus.proyectointegradortm.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import com.agus.proyectointegradortm.MyApplication
import com.agus.proyectointegradortm.R
import com.agus.proyectointegradortm.databinding.ActivityRegisterBinding
import com.agus.proyectointegradortm.models.User
import com.agus.proyectointegradortm.utils.Validator
import com.agus.proyectointegradortm.viewModels.UserViewModel
import com.google.android.material.snackbar.Snackbar

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

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
            val userFirstname = userName.toString()
            val userSurname = binding.etUserSurname.text.toString()
            val userEmail = email.toString()
            val userPassword = password.toString()

            val user = User(0, userFirstname, userSurname, userEmail, userPassword, "00/00/0000", "Sin definir", "https://cdn-icons-png.flaticon.com/512/1160/1160040.png?w=360")
            userViewModel.addUser(user)

            Snackbar.make(view, "Se ha registrado correctamente!", Snackbar.LENGTH_LONG).show()

            Handler().postDelayed({
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }, 1500)
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