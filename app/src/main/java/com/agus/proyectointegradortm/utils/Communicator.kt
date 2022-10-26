package com.agus.proyectointegradortm.utils

import com.agus.proyectointegradortm.models.Product

interface Communicator {
    fun passDataCom(product: Product)
}