package com.jimmyhernandez.data.source

import com.jimmyhernandez.domain.users.UserResponse

interface RemoteDataSource {

    suspend fun getListUsers(): List<UserResponse>
}