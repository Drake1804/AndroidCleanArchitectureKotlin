package com.drake1804.androidcleanarchitecturekotlin.di.users

import com.drake1804.androidcleanarchitecturekotlin.business.interactors.UsersInteractor
import com.drake1804.androidcleanarchitecturekotlin.business.interfaces.IUsersInteractor
import com.drake1804.androidcleanarchitecturekotlin.business.interfaces.IUsersPresenter
import com.drake1804.androidcleanarchitecturekotlin.business.interfaces.IUsersRepository
import com.drake1804.androidcleanarchitecturekotlin.data.db.DbService
import com.drake1804.androidcleanarchitecturekotlin.data.repositories.UsersRepository
import com.drake1804.androidcleanarchitecturekotlin.data.rest.RestService
import com.drake1804.androidcleanarchitecturekotlin.ui.users.UsersPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by drake1804 on 5/19/17.
 */
@Module
class UsersModule {

    @Provides
    @UsersScope
    fun provideUsersRepository(restService: RestService, dbService: DbService): IUsersRepository =
        UsersRepository(restService, dbService)

    @Provides
    @UsersScope
    fun provideUsersInteractor(usersRepository: IUsersRepository): IUsersInteractor =
        UsersInteractor(usersRepository)

    @Provides
    @UsersScope
    fun provideUsersPresenter(usersInteractor: IUsersInteractor): IUsersPresenter =
        UsersPresenter(usersInteractor)
}