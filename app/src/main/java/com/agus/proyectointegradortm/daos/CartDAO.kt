package com.agus.proyectointegradortm.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface CartDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createCart()
}