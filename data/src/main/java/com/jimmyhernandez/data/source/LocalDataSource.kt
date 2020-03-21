package com.jimmyhernandez.data.source

import com.jimmyhernandez.domain.users.UserResponse

interface LocalDataSource {

    suspend fun isEmpty(): Boolean
    suspend fun saveListUsers(users: List<UserResponse>)
    suspend fun getAllUsers(): List<UserResponse>

}