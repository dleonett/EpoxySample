package com.leonett.photofeed.ui.feature.hub.di

import com.leonett.photofeed.ui.feature.hub.HubFragment
import dagger.Subcomponent

@Subcomponent
interface HubComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): HubComponent
    }

    fun inject(fragment: HubFragment)

}