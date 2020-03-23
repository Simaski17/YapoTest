package com.jimmyhernandez.data.repository

import com.jimmyhernandez.data.source.LocalDataSource
import com.jimmyhernandez.data.source.RemoteDataSource
import com.jimmyhernandez.domain.albums.Albums
import com.jimmyhernandez.domain.photos.Photos

class AlbumsRepository(private val localDataSource: LocalDataSource, private val remoteDataSource: RemoteDataSource)  {

    suspend fun getListAlbums(albums: Int): List<Albums> {
        var albums = remoteDataSource.getListAlbums(albums)
        localDataSource.saveAlbumsList((albums as List<Albums>?)!!)
        return albums
    }

    suspend fun findById(id: Int): List<Albums> {
        return localDataSource.findAlbumById(id)
    }

    suspend fun getListAlbumsDetail(albums: Int): List<Photos> {
        var albumsDetails = remoteDataSource.getListAlbumsDetail(albums)
        localDataSource.saveAlbumsDetailList((albumsDetails as List<Photos>?)!!)
        return albumsDetails
    }

    suspend fun findAlbumDetailById(id: Int): List<Photos> {
        return localDataSource.findAlbumDetailById(id)
    }

}