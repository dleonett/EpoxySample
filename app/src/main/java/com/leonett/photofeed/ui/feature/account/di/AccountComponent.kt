package com.leonett.photofeed.ui.feature.account.di

import com.leonett.photofeed.ui.feature.account.AccountFragment
import dagger.Subcomponent

@Subcomponent
interface AccountComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): AccountComponent
    }

    fun inject(fragment: AccountFragment)

}