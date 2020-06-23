package com.leonett.photofeed.di

import com.leonett.photofeed.ui.feature.account.di.AccountComponent
import com.leonett.photofeed.ui.feature.feed.di.FeedComponent
import com.leonett.photofeed.ui.feature.login.di.LoginComponent
import com.leonett.photofeed.ui.feature.profile.di.ProfileComponent
import com.leonett.photofeed.ui.feature.splash.di.SplashComponent
import dagger.Component

@AppScope
@Component(modules = [ApplicationModule::class, SubcomponentsModule::class])
interface ApplicationComponent {

    fun splashComponent(): SplashComponent.Factory
    fun loginComponent(): LoginComponent.Factory
    fun accountComponent(): AccountComponent.Factory
    fun feedComponent(): FeedComponent.Factory
    fun profileComponent(): ProfileComponent.Factory

}