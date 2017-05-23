package com.drake1804.androidcleanarchitecturekotlin.di.application

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by drake1804 on 5/19/17.
 */
@Module
class ApplicationModule(private val context: Context) {
    @Provides
    @Singleton
    fun provideContext(): Context = context
}