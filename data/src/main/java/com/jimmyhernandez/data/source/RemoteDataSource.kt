package com.jimmyhernandez.data.source

import com.jimmyhernandez.domain.albums.Albums
import com.jimmyhernandez.domain.photos.Photos
import com.jimmyhernandez.domain.users.UserResponse

interface RemoteDataSource {

    suspend fun getListUsers(): List<UserResponse>

    suspend fun getListAlbums(albums: Int): List<Albums>

    suspend fun getListAlbumsDetail(albums: Int): List<Photos>

}