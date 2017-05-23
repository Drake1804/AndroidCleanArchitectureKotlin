package com.drake1804.androidcleanarchitecturekotlin.di.application

import com.drake1804.androidcleanarchitecturekotlin.data.db.DbService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by drake1804 on 5/19/17.
 */
@Module
class DbModule {

    @Provides
    @Singleton
    fun provideDbService(): DbService = DbService()
}