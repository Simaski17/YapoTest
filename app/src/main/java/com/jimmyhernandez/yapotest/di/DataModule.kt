package com.jimmyhernandez.yapotest.di

import com.jimmyhernandez.data.repository.UsersRepository
import com.jimmyhernandez.data.source.RemoteDataSource
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    fun usersRepositoryProvider(remoteDataSource: RemoteDataSource) = UsersRepository(remoteDataSource)

}