package com.leonett.epoxysample.di

import com.leonett.epoxysample.ui.feature.feed.FeedFragment
import com.leonett.epoxysample.ui.feature.profile.ProfileFragment
import dagger.Component

@AppScope
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(fragment: FeedFragment)
    fun inject(fragment: ProfileFragment)

}