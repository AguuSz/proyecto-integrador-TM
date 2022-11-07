package com.agus.proyectointegradortm.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.agus.proyectointegradortm.models.User

@Dao
interface UserDAO {

    @Query("SELECT * FROM User ORDER BY id ASC")
    fun getAll(): LiveData<List<User>>

    @Query("SELECT * FROM User WHERE id = :id")
    fun getUserByID(id: Int): User

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(people: List<User>)

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun delete(user: User)
}