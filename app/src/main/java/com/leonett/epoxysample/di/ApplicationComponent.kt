package com.leonett.epoxysample.di

import com.leonett.epoxysample.ui.feature.feed.di.FeedComponent
import com.leonett.epoxysample.ui.feature.profile.di.ProfileComponent
import dagger.Component

@AppScope
@Component(modules = [ApplicationModule::class, SubcomponentsModule::class])
interface ApplicationComponent {

    fun feedComponent(): FeedComponent.Factory
    fun profileComponent(): ProfileComponent.Factory

}