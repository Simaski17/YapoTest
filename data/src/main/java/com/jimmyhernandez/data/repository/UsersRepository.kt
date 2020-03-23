package com.jimmyhernandez.data.repository

import com.jimmyhernandez.data.source.LocalDataSource
import com.jimmyhernandez.data.source.RemoteDataSource
import com.jimmyhernandez.domain.users.UserResponse

class UsersRepository(private val localDataSource: LocalDataSource, private val remoteDataSource: RemoteDataSource) {

    suspend fun getListUsers(): List<UserResponse> {
        var users = remoteDataSource.getListUsers()
        localDataSource.saveListUsers((users as List<UserResponse>?)!!)
        return users
    }

    suspend fun getAllUsers(): List<UserResponse> {
        return localDataSource.getAllUsers()
    }

    suspend fun getCount(): Boolean {
        return localDataSource.isEmpty()
    }

    suspend fun findById(id: Int): UserResponse = localDataSource.findById(id)


}