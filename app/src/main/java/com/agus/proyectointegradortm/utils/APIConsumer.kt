package com.agus.proyectointegradortm.utils

import com.agus.proyectointegradortm.db.APIClient
import com.agus.proyectointegradortm.models.Product
import com.agus.proyectointegradortm.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

class APIConsumer {
    companion object {
        fun getUsers(): List<User> {
            var userList = emptyList<User>()
            runBlocking(Dispatchers.IO) {
                val users = APIClient.service.getUsers()
                val body = users.execute().body()
                if (body != null) {
                    userList = body
                }
            }
            return userList
        }

        fun getProducts(): List<Product> {
            var productList = emptyList<Product>()
            runBlocking(Dispatchers.IO) {
                val products = APIClient.service.getProducts()
                val body = products.execute().body()
                if (body != null) {
                    productList = body
                }
            }
            return productList
        }
    }
}