package com.agus.proyectointegradortm.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.agus.proyectointegradortm.db.UserDB
import com.agus.proyectointegradortm.models.Order
import com.agus.proyectointegradortm.repositories.OrderRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class OrderViewModel (application: Application): AndroidViewModel(application) {
    val getAllOrders: LiveData<List<Order>>
    private val repository: OrderRepository

    init {
        val orderDAO = UserDB.getDatabase(application).orderDAO()
        repository = OrderRepository(orderDAO)
        getAllOrders = repository.getAll
    }

    fun getOrderById(id: Int): Order {
        var order: Order
        runBlocking(Dispatchers.IO) {
            order = repository.getOrderById(id)
        }
        return order
    }

    fun addOrder(order: Order) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addOrder(order)
        }
    }

    fun deleteOrder(order: Order) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(order)
        }
    }
}