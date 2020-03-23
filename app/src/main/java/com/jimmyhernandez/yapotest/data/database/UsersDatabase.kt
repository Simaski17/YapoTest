package com.jimmyhernandez.yapotest.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UserResponse::class, Albums::class, Photos::class], version = 1)
abstract class UsersDatabase : RoomDatabase() {

    abstract fun usersDao(): UserResponseDao

}