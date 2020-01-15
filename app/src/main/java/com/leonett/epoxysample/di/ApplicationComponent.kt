package com.leonett.epoxysample.di

import com.leonett.epoxysample.ui.feature.main.MainActivity
import dagger.Component

@Component
interface ApplicationComponent {

    fun inject(activity: MainActivity)

}