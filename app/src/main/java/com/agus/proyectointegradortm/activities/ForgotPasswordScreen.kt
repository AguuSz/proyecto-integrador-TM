package com.agus.proyectointegradortm.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.agus.proyectointegradortm.databinding.ActivityForgotPasswordBinding

class ForgotPasswordScreen : AppCompatActivity() {
    private lateinit var binding: ActivityForgotPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSubmit.setOnClickListener {
            Toast.makeText(this, "Se ha enviado un correo a ${binding.etEmail.text}", Toast.LENGTH_SHORT).show()
        }
    }
}