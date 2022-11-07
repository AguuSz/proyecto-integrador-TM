package com.agus.proyectointegradortm.repositories

import androidx.lifecycle.LiveData
import com.agus.proyectointegradortm.daos.UserDAO
import com.agus.proyectointegradortm.models.User

class UserRepository(private val userDAO: UserDAO) {

    val getAll: LiveData<List<User>> = userDAO.getAll()

    suspend fun getUserById(id: Int): User {
        return userDAO.getUserByID(id)
    }

    suspend fun addUser(user: User) {
        userDAO.addUser(user)
    }

    suspend fun addUser(people: List<User>) {
        userDAO.addUser(people)
    }

    suspend fun updateUser(user: User) {
        userDAO.updateUser(user)
    }

    suspend fun delete(user: User) {
        userDAO.delete(user)
    }
}