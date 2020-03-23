package com.jimmyhernandez.yapotest.di

import com.jimmyhernandez.data.repository.AlbumsRepository
import com.jimmyhernandez.data.repository.UsersRepository
import com.jimmyhernandez.data.source.LocalDataSource
import com.jimmyhernandez.data.source.RemoteDataSource
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    fun usersRepositoryProvider(localDataSource: LocalDataSource, remoteDataSource: RemoteDataSource) = UsersRepository(localDataSource, remoteDataSource)

    @Provides
    fun albumsRepositoryProvider(localDataSource: LocalDataSource, remoteDataSource: RemoteDataSource) = AlbumsRepository(localDataSource, remoteDataSource)

}