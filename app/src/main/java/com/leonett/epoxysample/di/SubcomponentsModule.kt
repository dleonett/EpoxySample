package com.leonett.epoxysample.di

import com.leonett.epoxysample.ui.feature.feed.di.FeedComponent
import com.leonett.epoxysample.ui.feature.profile.di.ProfileComponent
import dagger.Module

@Module(subcomponents = [FeedComponent::class, ProfileComponent::class])
class SubcomponentsModule