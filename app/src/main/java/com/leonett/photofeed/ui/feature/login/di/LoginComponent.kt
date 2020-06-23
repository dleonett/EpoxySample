package com.leonett.photofeed.ui.feature.login.di

import com.leonett.photofeed.ui.feature.login.LoginFragment
import dagger.Subcomponent

@Subcomponent
interface LoginComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): LoginComponent
    }

    fun inject(fragment: LoginFragment)

}