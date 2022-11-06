package com.agus.proyectointegradortm.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.agus.proyectointegradortm.MyApplication
import com.agus.proyectointegradortm.databinding.ActivityProfileBinding
import com.agus.proyectointegradortm.providers.UsersProvider
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val actionBar = supportActionBar
        actionBar?.title = "Perfil"

        var user = UsersProvider.getUserByID(MyApplication.preferences.getUserID())

        Glide.with(this).load(user.profileImage).into(binding.ivProfileImage)
        binding.etUsername.setText(user.userName)
        binding.etUserSurname.setText(user.userSurname)
        binding.etUserEmail.setText(user.email)
        binding.etUserAddress.setText(user.address)
        binding.etUserBirthdate.setText(user.birthDate)

        binding.btnSave.setOnClickListener {
            // TODO: Implementar la funcion de guardar
            Snackbar.make(binding.root, "Se han guardado los datos!", Snackbar.LENGTH_SHORT).show()
        }

    }
}