package com.agus.proyectointegradortm.providers

import com.agus.proyectointegradortm.models.User

class UsersProvider {
    companion object {
        val userList = listOf<User>(
            User(
                0,
                "Agustin",
                "Sepulveda",
                "agus.sepu2000@hotmail.com",
                "pass123",
                "02/08/2000",
                "Direccion estandar 1",
                "https://robohash.org/1?set=set2&size=180x180"
            ),
            User(
                1,
                "Agustin",
                "Sanguesa",
                "asanguesa030@alumnos.iua.edu.ar",
                "pass123",
                "09/04/2002",
                "Direccion estandar 2",
                "https://robohash.org/2?set=set2&size=180x180"
            ),
            User(
                2,
                "Gaston",
                "Camargo",
                "gcamargo@gmail.com",
                "pass123",
                "27/04/2000",
                "Direccion estandar 3",
                "https://robohash.org/3?set=set2&size=180x180"
            ),
            User(
                3,
                "Lucas",
                "Rodriguez",
                "luquitas_rodriguez@gmail.com",
                "pass123",
                "01/06/1990",
                "Direccion estandar 4",
                "https://robohash.org/4?set=set2&size=180x180"
            ),
            User(
                4,
                "Juan",
                "Perez",
                "jperez@gmail.com",
                "pass123",
                "04/12/1995",
                "Direccion estandar 5",
                "https://robohash.org/5?set=set2&size=180x180"
            ),

        )

        fun getUserByID(id: Int): User {
            return userList[id]
        }
    }
}