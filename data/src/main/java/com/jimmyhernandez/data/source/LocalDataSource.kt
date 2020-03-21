package com.jimmyhernandez.data.source

import com.jimmyhernandez.domain.users.UserResponse

interface LocalDataSource {

    suspend fun getListUsers(): List<UserResponse>

}