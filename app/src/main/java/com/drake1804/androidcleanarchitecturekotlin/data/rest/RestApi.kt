package com.drake1804.androidcleanarchitecturekotlin.data.rest

import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Created by drake1804 on 5/19/17.
 */
interface RestApi {

    @GET("/users")
    fun getUsers(): Observable<List<UserModel>>

}