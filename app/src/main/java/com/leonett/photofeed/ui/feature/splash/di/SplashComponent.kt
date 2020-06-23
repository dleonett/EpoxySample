package com.leonett.photofeed.ui.feature.splash.di

import com.leonett.photofeed.ui.feature.splash.SplashActivity
import dagger.Subcomponent

@Subcomponent
interface SplashComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): SplashComponent
    }

    fun inject(activity: SplashActivity)

}