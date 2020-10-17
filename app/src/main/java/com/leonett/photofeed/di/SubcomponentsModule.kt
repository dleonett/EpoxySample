package com.leonett.photofeed.di

import com.leonett.photofeed.ui.feature.detail.post.di.PostDetailComponent
import com.leonett.photofeed.ui.feature.feed.di.FeedComponent
import com.leonett.photofeed.ui.feature.profile.di.ProfileComponent
import dagger.Module

@Module(subcomponents = [FeedComponent::class, ProfileComponent::class, PostDetailComponent::class])
class SubcomponentsModule