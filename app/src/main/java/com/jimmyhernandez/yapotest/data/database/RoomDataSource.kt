package com.jimmyhernandez.yapotest.data.database

import com.jimmyhernandez.data.source.LocalDataSource
import com.jimmyhernandez.domain.users.UserResponse
import com.jimmyhernandez.yapotest.data.toDomainuser
import com.jimmyhernandez.yapotest.data.toRoomUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RoomDataSource(db: UsersDatabase): LocalDataSource {

    private val usersDao = db.usersDao()

    override suspend fun isEmpty(): Boolean =
        withContext(Dispatchers.IO) { usersDao.usersCount() <= 0 }

    override suspend fun saveListUsers(users: List<UserResponse>) {
        withContext(Dispatchers.IO) { usersDao.insertUsers(users = users.map { it.toRoomUser() }) }
    }

    override suspend fun getAllUsers(): List<UserResponse> = withContext(Dispatchers.IO) {
        usersDao.getAllUsers().map { it.toDomainuser() }
    }

}