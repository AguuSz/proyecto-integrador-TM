package com.agus.proyectointegradortm.models

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var userName: String,
    var userSurname: String,
    var email: String,
    var password: String,
    var birthDate: String,
    var address: String,
    var profileImage: String
)