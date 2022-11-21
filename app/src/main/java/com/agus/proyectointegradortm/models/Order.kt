package com.agus.proyectointegradortm.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "order")
data class Order (
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var idUser: Int,
//    var productsIdList: List<Int>,
    var date: String
)