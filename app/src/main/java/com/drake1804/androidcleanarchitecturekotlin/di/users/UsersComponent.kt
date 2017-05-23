package com.drake1804.androidcleanarchitecturekotlin.di.users

import com.drake1804.androidcleanarchitecturekotlin.ui.users.BlankFragment
import dagger.Subcomponent

/**
 * Created by drake1804 on 5/20/17.
 */
@Subcomponent(modules = arrayOf(UsersModule::class))
@UsersScope
interface UsersComponent {
    fun inject(blankFragment: BlankFragment)
}