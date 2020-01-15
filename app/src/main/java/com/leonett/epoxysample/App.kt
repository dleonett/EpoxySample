package com.leonett.epoxysample

import android.app.Application
import com.leonett.epoxysample.di.DaggerApplicationComponent

class App : Application() {

    val appComponent = DaggerApplicationComponent.create()

}