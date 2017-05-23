package com.drake1804.androidcleanarchitecturekotlin.data.rest

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

/**
 * Created by drake1804 on 5/19/17.
 */

class RestService(val restApi: RestApi) {

    fun getUsers(): Observable<List<UserModel>> {
        return restApi.getUsers().subscribeOn(Schedulers.io())
    }
}