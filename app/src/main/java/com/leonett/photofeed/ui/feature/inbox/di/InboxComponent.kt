package com.leonett.photofeed.ui.feature.inbox.di

import com.leonett.photofeed.ui.feature.inbox.InboxFragment
import dagger.Subcomponent

@Subcomponent
interface InboxComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): InboxComponent
    }

    fun inject(fragment: InboxFragment)

}