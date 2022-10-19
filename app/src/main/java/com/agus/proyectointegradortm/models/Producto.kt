package com.agus.proyectointegradortm.models

data class Producto(
    var title: String = "Producto",
    var description: String = "No se detallo una descripcion para este producto",
    var price: Int,
    var type: String,
    var imageURL: String = "https://www.phswarnerhoward.co.uk/assets/images/no_img_avaliable.jpg"
)