package com.agus.proyectointegradortm.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.agus.proyectointegradortm.models.Order

@Dao
interface OrderDAO {
    @Query("SELECT * FROM `order` ORDER BY id ASC")
    fun getAll(): LiveData<List<Order>>

    @Query("SELECT * FROM `order` WHERE id = :id")
    fun getOrderById(id: Int): Order

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addOrder(Order: Order)

    @Update
    suspend fun updateOrder(Order: Order)

    @Delete
    suspend fun delete(Order: Order)
}