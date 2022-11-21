package com.agus.proyectointegradortm.activities

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.agus.proyectointegradortm.MyApplication
import com.agus.proyectointegradortm.databinding.ActivityProfileBinding
import com.agus.proyectointegradortm.fragments.DatePickerFragment
import com.agus.proyectointegradortm.models.User
import com.agus.proyectointegradortm.viewModels.UserViewModel
import com.bumptech.glide.Glide
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.android.material.snackbar.Snackbar
import java.util.Calendar

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    private lateinit var userViewModel: UserViewModel
    private lateinit var user: User
    private lateinit var uri: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val actionBar = supportActionBar
        actionBar?.title = "Perfil"

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        user = userViewModel.getUserById(MyApplication.preferences.getUserID())

        Glide.with(applicationContext).load(user.profileImage).into(binding.ivProfileImage)
        binding.etUsername.setText(user.userName)
        binding.etUserSurname.setText(user.userSurname)
        binding.etUserEmail.setText(user.email)
        binding.etUserAddress.setText(user.address)
        binding.etUserBirthdate.setText(user.birthDate)

        binding.ivProfileImage.setOnClickListener {
            Log.d("testeo", "Clickeado el boton")
            ImagePicker.with(this)
                .crop()
                .maxResultSize(1080, 1080)
                .start()
        }

        binding.etUserBirthdate.setOnClickListener {
            showDatePickerDialog()
        }

        binding.btnSave.setOnClickListener {
            saveUser()
        }
    }

    private fun showDatePickerDialog() {
        val datePicker = DatePickerFragment { day, month, year -> onDateSelected(day, month, year) }
        datePicker.show(supportFragmentManager, "datePicker")
    }

    private fun onDateSelected(day: Int, month: Int, year: Int) {
        binding.etUserBirthdate.setText("$day/${month + 1}/$year")
    }

    private fun saveUser() {
        user.userName = binding.etUsername.text.toString()
        user.userSurname = binding.etUserSurname.text.toString()
        user.email = binding.etUserEmail.text.toString()
        user.address = binding.etUserAddress.text.toString()
        user.birthDate = binding.etUserBirthdate.text.toString()

        userViewModel.updateUser(user)

        Snackbar.make(binding.root, "Se han guardado los datos!", Snackbar.LENGTH_SHORT).show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (resultCode) {
            Activity.RESULT_OK -> {
                uri = data?.data!!
                user.profileImage = uri.toString()
                Glide.with(applicationContext).load(uri).into(binding.ivProfileImage)
            }
            ImagePicker.RESULT_ERROR -> Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
            else -> Toast.makeText(this, "Task cancelled", Toast.LENGTH_SHORT).show()
        }
    }
}