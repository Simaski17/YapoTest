package com.jimmyhernandez.yapotest

import android.app.Application
import com.jimmyhernandez.yapotest.di.DaggerMyYapoComponent
import com.jimmyhernandez.yapotest.di.MyYapoComponent

class YapoApp : Application() {

    lateinit var component: MyYapoComponent
        private set

    override fun onCreate() {
        super.onCreate()

        component = DaggerMyYapoComponent.factory().create(this)
    }

}