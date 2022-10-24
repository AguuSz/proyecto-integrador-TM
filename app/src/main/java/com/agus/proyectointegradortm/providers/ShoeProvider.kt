package com.agus.proyectointegradortm.providers

import com.agus.proyectointegradortm.models.Product

class ShoeProvider {
    companion object {
        val shoeList = listOf<Product>(
            Product(
                1,
                "Jordan 1 High",
                "Descripcion generica",
                175,
                "shoe",
                "urlALaImagen.com"
            ),
            Product(
                2,
                "Jordan 1 Low",
                "Descripcion generica",
                120,
                "shoe",
                "urlALaImagen.com"
            ),
            Product(
                3,
                "Air Force 1",
                "Descripcion generica",
                120,
                "shoe",
                "urlALaImagen.com"
            ),
            Product(
                4,
                "SB Dunk",
                "Descripcion generica",
                175,
                "shoe",
                "urlALaImagen.com"
            ),
            Product(
                5,
                "Vans Classic",
                "Descripcion generica",
                100,
                "shoe",
                "urlALaImagen.com"
            ),

        )
    }
}