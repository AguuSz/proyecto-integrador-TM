package com.agus.proyectointegradortm.providers

import com.agus.proyectointegradortm.models.Product

class ShirtProvider {
    companion object {
        val shirtList = listOf<Product>(
            Product(
                100,
                "Remera blanca",
                "Descripcion generica",
                10,
                "shirt",
                "urlALaImagen.com"
            ),
            Product(
                101,
                "Remera roja",
                "Descripcion generica",
                10,
                "shoe",
                "urlALaImagen.com"
            ),
            Product(
                102,
                "Remera Essentials",
                "Descripcion generica",
                20,
                "shoe",
                "urlALaImagen.com"
            ),
            Product(
                103,
                "Remera grafica",
                "Descripcion generica",
                20,
                "shoe",
                "urlALaImagen.com"
            ),
            Product(
                104,
                "Remera grafica TS",
                "Descripcion generica",
                25,
                "shoe",
                "urlALaImagen.com"
            )
        )
    }
}