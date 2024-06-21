package com.example.mondly

import android.app.Application
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.components.SingletonComponent


@HiltAndroidApp
open class MondlyApplication : Application() {

    /**
     * Constants
     */
    companion object {
        @JvmStatic
        lateinit var app: MondlyApplication
    }

    @EntryPoint
    @InstallIn(SingletonComponent::class)
    internal interface DaggerHiltEntryPoint {
    }

    /**
     * Application methods
     */
    override fun onCreate() {
        super.onCreate()
        app = this
    }
}
