package com.jimmyhernandez.data.repository

import com.jimmyhernandez.data.source.RemoteDataSource
import com.jimmyhernandez.domain.users.UserResponse

class UsersRepository(private val remoteDataSource: RemoteDataSource) {

    suspend fun getListUsers(): List<UserResponse> {
        return remoteDataSource.getListUsers()
    }

}