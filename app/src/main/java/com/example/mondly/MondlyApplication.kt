package com.example.mondly

import android.app.Application
import com.example.data.di.getModule
import com.example.presentation.di.modules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.module.Module


open class MondlyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MondlyApplication)
            val dataModule = getModule(this@MondlyApplication)
            val presentationModules = modules
            val allDependencies = mutableListOf<Module>()
            allDependencies += dataModule
            allDependencies += presentationModules
            modules(allDependencies.toList())
        }
    }
}
