package com.agus.proyectointegradortm.models.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.agus.proyectointegradortm.models.Cart
import com.agus.proyectointegradortm.models.User

data class UserAndCart(
    @Embedded val user: User,

    @Relation(
        parentColumn = "id",
        entityColumn = "cartUserId"
    )
    val cart: Cart
)
