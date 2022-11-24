package com.agus.proyectointegradortm.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart")
data class Cart (
    @PrimaryKey(autoGenerate = true)
    var cartId: Int,
    var cartUserId: Int
)