package com.leonett.epoxysample.ui.feature.profile.di

import com.leonett.epoxysample.ui.feature.profile.ProfileFragment
import dagger.Subcomponent

@Subcomponent
interface ProfileComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): ProfileComponent
    }

    fun inject(fragment: ProfileFragment)

}