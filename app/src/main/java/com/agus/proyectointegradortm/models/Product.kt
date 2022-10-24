package com.agus.proyectointegradortm.models

import android.os.Parcel
import android.os.Parcelable

data class Product(
    var id: Int,
    var title: String = "Producto",
    var description: String = "No se detallo una descripcion para este producto",
    var price: Int,
    var type: String,
    var imageURL: String = "https://www.phswarnerhoward.co.uk/assets/images/no_img_avaliable.jpg"
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeInt(price)
        parcel.writeString(type)
        parcel.writeString(imageURL)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Product> {
        override fun createFromParcel(parcel: Parcel): Product {
            return Product(parcel)
        }

        override fun newArray(size: Int): Array<Product?> {
            return arrayOfNulls(size)
        }
    }
}