package com.drake1804.androidcleanarchitecturekotlin.data.repositories

import com.drake1804.androidcleanarchitecturekotlin.data.db.DbService
import com.drake1804.androidcleanarchitecturekotlin.data.rest.RestService
import com.drake1804.androidcleanarchitecturekotlin.data.rest.UserModel
import com.drake1804.androidcleanarchitecturekotlin.business.interfaces.IUsersRepository
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

/**
 * Created by drake1804 on 5/19/17.
 */

class UsersRepository(val restService: RestService, val dbService: DbService) : IUsersRepository {

    override fun getUsers(): Observable<List<UserModel>> {
        val usersDbObservable = dbService.getUsers().subscribeOn(Schedulers.computation())
        val usersRestObservable = restService.getUsers()
                .subscribeOn(Schedulers.io())
                .doOnNext { users -> dbService.saveUsers(users) }
                .subscribeOn(Schedulers.computation())

        return Observable.concat(usersDbObservable, usersRestObservable)
    }
}