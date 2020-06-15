package com.leonett.epoxysample.ui.feature.feed.di

import com.leonett.epoxysample.ui.feature.feed.FeedFragment
import dagger.Subcomponent

@Subcomponent
interface FeedComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): FeedComponent
    }

    fun inject(fragment: FeedFragment)

}