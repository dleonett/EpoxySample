package com.leonett.photofeed.ui.feature.detail.post.di

import com.leonett.photofeed.ui.feature.detail.post.PostDetailFragment
import dagger.Subcomponent

@Subcomponent
interface PostDetailComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): PostDetailComponent
    }

    fun inject(fragment: PostDetailFragment)

}