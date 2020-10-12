package com.leonett.photofeed

import androidx.multidex.MultiDexApplication
import com.airbnb.epoxy.Carousel
import com.leonett.photofeed.di.ApplicationComponent
import com.leonett.photofeed.di.ApplicationModule
import com.leonett.photofeed.di.DaggerApplicationComponent

class App : MultiDexApplication() {

    lateinit var appComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(applicationContext))
            .build()

        Carousel.setDefaultGlobalSnapHelperFactory(null)
    }

}