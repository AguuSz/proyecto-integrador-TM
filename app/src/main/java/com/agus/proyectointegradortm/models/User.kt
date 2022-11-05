package com.agus.proyectointegradortm.models

import android.os.Parcel
import android.os.Parcelable

data class User(
    var id: Int,
    var userName: String,
    var userSurname: String,
    var email: String,
    var birthDate: String,
    var address: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(userName)
        parcel.writeString(userSurname)
        parcel.writeString(email)
        parcel.writeString(birthDate)
        parcel.writeString(address)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }

}