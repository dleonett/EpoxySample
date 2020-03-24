package com.leonett.epoxysample.di

import com.leonett.epoxysample.ui.feature.main.MainActivity
import dagger.Component

@AppScope
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(activity: MainActivity)

}