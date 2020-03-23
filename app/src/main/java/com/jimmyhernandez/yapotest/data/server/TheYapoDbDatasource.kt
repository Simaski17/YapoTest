package com.jimmyhernandez.yapotest.data.server

import com.jimmyhernandez.data.source.RemoteDataSource
import com.jimmyhernandez.domain.albums.Albums
import com.jimmyhernandez.domain.photos.Photos
import com.jimmyhernandez.domain.users.UserResponse

class TheYapoDbDatasource(private val theYapoDbService: TheYapoDbService) : RemoteDataSource {

    override suspend fun getListUsers(): List<UserResponse> = theYapoDbService.getListUsers().execute().body()!!

    override suspend fun getListAlbums(albums: Int): List<Albums> = theYapoDbService.getListAlbums(albums).execute().body()!!

    override suspend fun getListAlbumsDetail(albums: Int): List<Photos> = theYapoDbService.getListAlbumsDetail(albums).execute().body()!!
}