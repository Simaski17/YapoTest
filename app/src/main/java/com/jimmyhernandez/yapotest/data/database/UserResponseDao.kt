package com.jimmyhernandez.yapotest.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserResponseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsers(users: List<UserResponse>)

    @Query("SELECT COUNT(id) FROM UserResponse")
    fun usersCount(): Int

    @Query("SELECT * FROM UserResponse")
    fun getAllUsers(): List<UserResponse>


}