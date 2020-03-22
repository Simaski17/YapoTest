package com.jimmyhernandez.yapotest.di

import android.app.Application
import com.jimmyhernandez.yapotest.ui.main.MainActivityComponent
import com.jimmyhernandez.yapotest.ui.main.MainActivityModule
import com.jimmyhernandez.yapotest.ui.user.UsersActivityComponent
import com.jimmyhernandez.yapotest.ui.user.UsersActivityModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DataModule::class])
interface MyYapoComponent {

    fun plus(module: MainActivityModule): MainActivityComponent
    fun plus(module: UsersActivityModule): UsersActivityComponent

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: Application): MyYapoComponent
    }

}