package com.drake1804.androidcleanarchitecturekotlin

import android.app.Application
import android.content.Context
import com.drake1804.androidcleanarchitecturekotlin.di.application.ApplicationComponent
import com.drake1804.androidcleanarchitecturekotlin.di.application.ApplicationModule
import com.drake1804.androidcleanarchitecturekotlin.di.application.DaggerApplicationComponent
import com.drake1804.androidcleanarchitecturekotlin.di.users.UsersComponent
import com.drake1804.androidcleanarchitecturekotlin.di.users.UsersModule
import io.realm.Realm
import io.realm.RealmConfiguration
import timber.log.Timber

/**
 * Created by drake1804 on 5/19/17.
 */

class ACAKApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent

    companion object {
        @JvmStatic fun get(context: Context): ACAKApplication = context.applicationContext as ACAKApplication
    }

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this)).build()

        initRealm()

        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }

    fun plusUsersComponent() = applicationComponent.plusUsersComponent(UsersModule())

    fun initRealm() {
        Realm.init(applicationContext)
        Realm.setDefaultConfiguration(RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build())
    }
}