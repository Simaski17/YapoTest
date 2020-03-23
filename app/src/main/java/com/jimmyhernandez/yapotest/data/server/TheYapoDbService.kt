package com.jimmyhernandez.yapotest.data.server

import com.jimmyhernandez.domain.albums.Albums
import com.jimmyhernandez.domain.photos.Photos
import com.jimmyhernandez.domain.users.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TheYapoDbService {

    @GET("users")
    fun getListUsers(): Call<List<UserResponse>>

    @GET("albums")
    fun getListAlbums(@Query("userId") userId: Int): Call<List<Albums>>

    @GET("photos")
    fun getListAlbumsDetail(@Query("albumId") albumId: Int): Call<List<Photos>>

}