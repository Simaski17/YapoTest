package com.jimmyhernandez.yapotest.di

import android.app.Application
import androidx.room.Room
import com.jimmyhernandez.data.source.LocalDataSource
import com.jimmyhernandez.data.source.RemoteDataSource
import com.jimmyhernandez.yapotest.data.database.RoomDataSource
import com.jimmyhernandez.yapotest.data.database.UsersDatabase
import com.jimmyhernandez.yapotest.data.server.TheYapoDbDatasource
import com.jimmyhernandez.yapotest.data.server.TheYapoDbService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun databaseProvider(app: Application) = Room.databaseBuilder(
        app,
        UsersDatabase::class.java,
        "users-db"
    ).build()

    @Provides
    fun localDataSourceProvider(db: UsersDatabase): LocalDataSource = RoomDataSource(db)

    @Singleton
    @Provides
    fun provideInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .callTimeout(1, TimeUnit.MINUTES)
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .addInterceptor(provideInterceptor())
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .client(provideHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofitServiceApi(): TheYapoDbService {
        return provideRetrofit().create(TheYapoDbService::class.java)
    }

    @Provides
    fun remoteDataSourceProvider(theItunesDbService: TheYapoDbService): RemoteDataSource = TheYapoDbDatasource(theItunesDbService)

}