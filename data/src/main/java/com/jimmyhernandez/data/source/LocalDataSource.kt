package com.jimmyhernandez.data.source

import com.jimmyhernandez.domain.albums.Albums
import com.jimmyhernandez.domain.photos.Photos
import com.jimmyhernandez.domain.users.UserResponse

interface LocalDataSource {

    suspend fun isEmpty(): Boolean
    suspend fun saveListUsers(users: List<UserResponse>)
    suspend fun getAllUsers(): List<UserResponse>
    suspend fun findById(id: Int): UserResponse
    suspend fun saveAlbumsList(albums: List<Albums>)
    suspend fun findAlbumById(id: Int): List<Albums>
    suspend fun saveAlbumsDetailList(albums: List<Photos>)
    suspend fun findAlbumDetailById(id: Int): List<Photos>

}