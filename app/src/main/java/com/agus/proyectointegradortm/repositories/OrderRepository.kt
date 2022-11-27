package com.agus.proyectointegradortm.repositories

import androidx.lifecycle.LiveData
import com.agus.proyectointegradortm.daos.OrderDAO
import com.agus.proyectointegradortm.models.Order

class OrderRepository(private val orderDAO: OrderDAO) {
    val getAll: LiveData<List<Order>> = orderDAO.getAll()

    suspend fun getOrderById(id: Int): Order {
        return orderDAO.getOrderById(id)
    }

    suspend fun addOrder(order: Order) {
        orderDAO.addOrder(order)
    }

    suspend fun delete(order: Order) {
        orderDAO.delete(order)
    }
}