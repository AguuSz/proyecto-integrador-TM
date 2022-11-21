package com.agus.proyectointegradortm.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.agus.proyectointegradortm.models.Product

@Dao
interface ProductDAO {
    @Query("SELECT * FROM Product ORDER BY id ASC")
    fun getAll(): LiveData<List<Product>>

    @Query("SELECT * FROM Product WHERE type = :type")
    fun getProductsByType(type: Int): List<Product>

    @Query("SELECT * FROM Product WHERE id = :id")
    fun getProductById(id: Int): Product

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addProduct(product: Product)

    @Update
    suspend fun updateProduct(product: Product)

    @Delete
    suspend fun delete(product: Product)
}