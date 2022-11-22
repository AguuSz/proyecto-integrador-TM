package com.agus.proyectointegradortm.db

import com.agus.proyectointegradortm.services.CategoryService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CategoryClient {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://mocki.io/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofit.create(CategoryService::class.java)
}