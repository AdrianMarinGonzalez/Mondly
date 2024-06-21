package com.example.mondly.di

import android.app.Application
import android.content.Context
import com.example.mondly.MondlyApplication.Companion.app
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * AppModule
 *
 * All the dependencies that will be necessary along the app life
 */

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /**-------------------------------------------------------------------------------------------*/
    /**                                                                                           */
    /**                                     GLOBAL DEPENDENCIES                                   */
    /**                                                                                           */
    /**-------------------------------------------------------------------------------------------*/

    @Provides
    @Singleton
    fun provideAppContext(): Context = app

}
