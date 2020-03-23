package com.jimmyhernandez.yapotest.data.database

import com.jimmyhernandez.data.source.LocalDataSource
import com.jimmyhernandez.domain.albums.Albums
import com.jimmyhernandez.domain.photos.Photos
import com.jimmyhernandez.domain.users.UserResponse
import com.jimmyhernandez.yapotest.data.*
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

    override suspend fun findById(id: Int): UserResponse  = withContext(Dispatchers.IO) {
       usersDao.findById(id).toDomainuser()
    }

    //ALBUMS
    override suspend fun saveAlbumsList(albums: List<Albums>) {
        withContext(Dispatchers.IO) { usersDao.insertAlbums(albums = albums.map { it.toRoomAlbums() }) }
    }

    override suspend fun findAlbumById(id: Int): List<Albums> = withContext(Dispatchers.IO) {
        usersDao.findAlbumsById(id).map { it.toDomainAlbum() }
    }

    override suspend fun saveAlbumsDetailList(albums: List<Photos>) {
        withContext(Dispatchers.IO) { usersDao.insertAlbumsDetails(albums = albums.map { it.toRoomPhotos() }) }
    }

    override suspend fun findAlbumDetailById(id: Int): List<Photos> = withContext(Dispatchers.IO) {
        usersDao.findAlbumDetailById(id).map { it.toDomainPhotos() }
    }

    override suspend fun updateUser(userResponse: UserResponse) {
        withContext(Dispatchers.IO) { usersDao.updateUser(userResponse.toRoomUser()) }
    }

    override suspend fun getFavoriteUsers(favorite: Boolean): List<UserResponse> = withContext(Dispatchers.IO) {
        usersDao.getFavoriteUser(true).map { it.toDomainuser() }
    }


}