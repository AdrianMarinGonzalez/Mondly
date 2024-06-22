package com.example.mondly

import android.app.Application
import com.example.data.di.getModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


open class MondlyApplication : Application() {

    val dataModules = getModules(this)

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MondlyApplication)
            modules(dataModules)
        }
    }
}
