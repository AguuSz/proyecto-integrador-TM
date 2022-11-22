package com.agus.proyectointegradortm.services

import com.agus.proyectointegradortm.models.Category
import retrofit2.Call
import retrofit2.http.GET

interface CategoryService {
    @GET("v1/72ee4c05-dbdc-4b99-8509-9cc27e9f6cb5")
    fun getCategories(): Call<Category>
}