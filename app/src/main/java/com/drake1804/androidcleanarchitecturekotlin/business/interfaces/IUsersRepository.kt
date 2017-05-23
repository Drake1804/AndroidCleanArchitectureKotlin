package com.drake1804.androidcleanarchitecturekotlin.business.interfaces

import com.drake1804.androidcleanarchitecturekotlin.data.rest.UserModel
import io.reactivex.Observable

/**
 * Created by drake1804 on 5/19/17.
 */
interface IUsersRepository {

    fun getUsers(): Observable<List<UserModel>>

}