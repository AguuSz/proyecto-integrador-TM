package com.agus.proyectointegradortm.services

import com.agus.proyectointegradortm.models.Category
import retrofit2.Call
import retrofit2.http.GET

interface CategoryService {
    @GET("v1/4cea5c4b-fdae-4a8f-b1e3-e7aecf49b11d")
    fun getCategories(): Call<Category>
}