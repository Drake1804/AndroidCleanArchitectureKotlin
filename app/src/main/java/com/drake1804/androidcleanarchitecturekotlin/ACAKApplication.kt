package com.drake1804.androidcleanarchitecturekotlin

import android.app.Application
import android.content.Context
import com.drake1804.androidcleanarchitecturekotlin.di.application.ApplicationComponent
import com.drake1804.androidcleanarchitecturekotlin.di.application.ApplicationModule
import com.drake1804.androidcleanarchitecturekotlin.di.application.DaggerApplicationComponent
import com.drake1804.androidcleanarchitecturekotlin.di.users.UsersComponent
import com.drake1804.androidcleanarchitecturekotlin.di.users.UsersModule

/**
 * Created by drake1804 on 5/19/17.
 */

class ACAKApplication : Application() {

    companion object {
        @JvmStatic lateinit var applicationComponent: ApplicationComponent
        @JvmStatic lateinit var usersComponent: UsersComponent

        @JvmStatic fun get(context: Context): ACAKApplication = context.applicationContext as ACAKApplication
    }

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this)).build()

        usersComponent = applicationComponent.plusUsersComponent(UsersModule())
    }

    fun plusUsersComponent(): UsersComponent {

    }

}