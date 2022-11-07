package com.agus.proyectointegradortm.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.agus.proyectointegradortm.daos.UserDAO
import com.agus.proyectointegradortm.models.User

@Database(
    entities = [User::class],
    version = 1
)
abstract class UserDB : RoomDatabase() {
    abstract fun userDAO(): UserDAO

    companion object {
        @Volatile
        private var INSTANCE: UserDB? = null

        fun getDatabase(context: Context): UserDB {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDB::class.java,
                    "user"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}