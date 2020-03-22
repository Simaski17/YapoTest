package com.jimmyhernandez.yapotest.data.server

import com.jimmyhernandez.data.source.RemoteDataSource
import com.jimmyhernandez.domain.users.UserResponse

class TheYapoDbDatasource(private val theYapoDbService: TheYapoDbService) : RemoteDataSource {

    override suspend fun getListUsers(): ArrayList<UserResponse> = theYapoDbService.getListUsers().execute().body()!!
}