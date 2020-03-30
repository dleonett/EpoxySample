package com.leonett.epoxysample

import androidx.multidex.MultiDexApplication
import com.leonett.epoxysample.di.ApplicationComponent
import com.leonett.epoxysample.di.ApplicationModule
import com.leonett.epoxysample.di.DaggerApplicationComponent

class App : MultiDexApplication() {

    lateinit var appComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(applicationContext))
            .build()
    }

}