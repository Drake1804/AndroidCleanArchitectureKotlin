package com.drake1804.androidcleanarchitecturekotlin.di.application

import com.drake1804.androidcleanarchitecturekotlin.di.users.UsersComponent
import com.drake1804.androidcleanarchitecturekotlin.di.users.UsersModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by drake1804 on 5/19/17.
 */
@Singleton
@Component(modules = arrayOf(ApplicationModule::class, NetworkModule::class, DbModule::class))
interface ApplicationComponent {
    fun plusUsersComponent(usersModule: UsersModule): UsersComponent
}