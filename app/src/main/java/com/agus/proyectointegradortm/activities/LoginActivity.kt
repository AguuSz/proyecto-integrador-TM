package com.agus.proyectointegradortm.activities

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.GravityCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.agus.proyectointegradortm.MyApplication
import com.agus.proyectointegradortm.R
import com.agus.proyectointegradortm.databinding.ActivityLoginBinding
import com.agus.proyectointegradortm.models.User
import com.agus.proyectointegradortm.providers.UsersProvider
import com.agus.proyectointegradortm.utils.Validator
import com.agus.proyectointegradortm.viewModels.UserViewModel
import com.google.android.material.snackbar.Snackbar

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var userViewModel: UserViewModel
    private var userList = emptyList<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        userViewModel.getAllUsers.observe(this, Observer { users ->
            userList = users
        })

        // Seteamos el texto en caso de que el usuario haya elegido guardarla
        val userEmail = MyApplication.preferences.getUserEmail()
        if (userEmail != "")
            binding.etEmail.setText(userEmail)

        // handleClickListeners
        binding.btnLogin.setOnClickListener {
            login(view, binding.etEmail.text, binding.etPassword.text)
        }
        binding.tvForgotPassword.setOnClickListener {
            goTo(ForgotPasswordScreen::class.java)
        }
        binding.btnRegister.setOnClickListener {
            goTo(RegisterActivity::class.java)
        }
    }

    private fun login(view: ConstraintLayout, email: Editable, password: Editable) {
        var user: User? = null
        if (email.isEmpty() || password.isEmpty()) {
            Snackbar.make(view, "Los datos no pueden estar vacios", Snackbar.LENGTH_LONG).show()
            return
        }
        if (!Validator.isAValidEmail(email.toString())) {
            Snackbar.make(view, "Email invalido", Snackbar.LENGTH_LONG).show()
            return
        }

        userList.forEach {
            if (it.email == email.toString()) {
                user = it
            }
        }

        if (user != null && user!!.password == password.toString()) {
            if (binding.cbKeepLoggedIn.isChecked) {
                MyApplication.preferences.setRemainingLogins(3)
            }
            if (binding.cbRememberEmail.isChecked) {
                MyApplication.preferences.setUserEmail(email.toString())
            } else {
                MyApplication.preferences.setUserEmail("")
            }
            MyApplication.preferences.setUserID(user!!.id)
            goTo(HomeActivity::class.java)
        } else {
            Snackbar.make(view, "No existe una cuenta con esos datos", Snackbar.LENGTH_LONG).show()
        }
    }

    fun goTo(activity: Class<*>) {
        val intent = Intent(this, activity)
        startActivity(intent)
    }

    override fun onBackPressed() {
        finishAffinity()
    }
}