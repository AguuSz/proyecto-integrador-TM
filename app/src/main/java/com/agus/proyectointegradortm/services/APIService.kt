package com.agus.proyectointegradortm.services

import com.agus.proyectointegradortm.models.Category
import com.agus.proyectointegradortm.models.Product
import com.agus.proyectointegradortm.models.User
import retrofit2.Call
import retrofit2.http.GET

interface APIService {
    @GET("v1/72ee4c05-dbdc-4b99-8509-9cc27e9f6cb5")
    fun getCategories(): Call<Category>

    @GET("v1/8cccc78b-ba73-4142-92b4-b940fa1f8f49")
    fun getUsers(): Call<List<User>>

    @GET("v1/bf323f91-f5d0-4969-82c5-5c7fb82e8390")
    fun getProducts(): Call<List<Product>>
}