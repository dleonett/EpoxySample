package com.leonett.photofeed.di

import com.leonett.photofeed.ui.feature.feed.di.FeedComponent
import com.leonett.photofeed.ui.feature.profile.di.ProfileComponent
import dagger.Module

@Module(subcomponents = [FeedComponent::class, ProfileComponent::class])
class SubcomponentsModule