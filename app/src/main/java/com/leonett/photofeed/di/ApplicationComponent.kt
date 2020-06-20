package com.leonett.photofeed.di

import com.leonett.photofeed.ui.feature.feed.di.FeedComponent
import com.leonett.photofeed.ui.feature.profile.di.ProfileComponent
import dagger.Component

@AppScope
@Component(modules = [ApplicationModule::class, SubcomponentsModule::class])
interface ApplicationComponent {

    fun feedComponent(): FeedComponent.Factory
    fun profileComponent(): ProfileComponent.Factory

}