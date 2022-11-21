package com.agus.proyectointegradortm.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.agus.proyectointegradortm.MyApplication
import com.agus.proyectointegradortm.databinding.ActivityChangePasswordBinding
import com.agus.proyectointegradortm.models.User
import com.agus.proyectointegradortm.viewModels.UserViewModel
import com.google.android.material.snackbar.Snackbar

class ChangePasswordActivity: AppCompatActivity() {
    private lateinit var binding: ActivityChangePasswordBinding
    private lateinit var user: User
    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChangePasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val actionBar = supportActionBar
        actionBar?.title = "Cambio de contraseña"

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        user = userViewModel.getUserById(MyApplication.preferences.getUserID())

        binding.btnSave.setOnClickListener {
            updateUserPassword()
        }
    }

    private fun updateUserPassword() {
        hideErrorMessages()
        when {
            // Contrasena actual incorrecta
            user.password != binding.etCurrentPassword.text.toString() -> {
                binding.tvCurrentPasswordError.text = "La contraseña es incorrecta"
                binding.tvCurrentPasswordError.visibility = View.VISIBLE
            }
            // Misma contrasena que antes
            binding.etCurrentPassword.text.toString() == binding.etNewPassword.text.toString() -> {
                binding.tvNewPasswordError.text = "La contraseña no puede ser igual a la anterior"
                binding.tvNewPasswordError.visibility = View.VISIBLE
            }
            // Nueva contrasena vacia
            binding.etNewPassword.text.toString().isEmpty() -> {
                binding.tvNewPasswordError.text = "La contraseña no puede estar vacia"
                binding.tvNewPasswordError.visibility = View.VISIBLE
            }
            // No coinciden la nueva contrasena
            binding.etNewPassword.text.toString() != binding.etNewPassword2.text.toString() -> {
                binding.tvNewPasswordError.text = "Las contraseñas no coinciden"
                binding.tvNewPasswordError.visibility = View.VISIBLE
            }
            else -> {
                user.password = binding.etNewPassword.text.toString()
                userViewModel.updateUser(user)

                Snackbar.make(binding.root, "Se han guardado los datos!", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun hideErrorMessages() {
        binding.tvCurrentPasswordError.text = ""
        binding.tvCurrentPasswordError.visibility = View.GONE
        binding.tvNewPasswordError.text = ""
        binding.tvNewPasswordError.visibility = View.GONE
    }
}