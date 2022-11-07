package com.agus.proyectointegradortm.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.agus.proyectointegradortm.db.UserDB
import com.agus.proyectointegradortm.models.User
import com.agus.proyectointegradortm.repositories.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class UserViewModel(application: Application): AndroidViewModel(application) {
    val getAllUsers: LiveData<List<User>>
    private val repository: UserRepository

    init {
        val userDAO = UserDB.getDatabase(application).userDAO()
        repository = UserRepository(userDAO)
        getAllUsers = repository.getAll
    }

    fun getUserById(id: Int): User {
        var user: User
        runBlocking (Dispatchers.IO) {
            user = repository.getUserById(id)
        }
        return user
    }

    fun addUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }

    fun addUser(people: List<User>) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(people)
        }
    }

    fun updateUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUser(user)
        }
    }

    fun delete(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(user)
        }
    }
}