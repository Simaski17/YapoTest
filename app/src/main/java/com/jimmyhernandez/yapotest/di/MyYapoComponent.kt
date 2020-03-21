package com.jimmyhernandez.yapotest.di

import android.app.Application
import com.jimmyhernandez.yapotest.ui.user.MainActivityComponent
import com.jimmyhernandez.yapotest.ui.user.MainActivityModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DataModule::class])
interface MyYapoComponent {

    fun plus(module: MainActivityModule): MainActivityComponent

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: Application): MyYapoComponent
    }

}