package com.jimmyhernandez.yapotest.data.server

import com.jimmyhernandez.domain.users.UserResponse
import retrofit2.Call
import retrofit2.http.GET

interface TheYapoDbService {

    @GET("users")
    fun getListUsers(): Call<ArrayList<UserResponse>>

}