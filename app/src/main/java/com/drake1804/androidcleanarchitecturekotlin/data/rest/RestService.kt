package com.drake1804.androidcleanarchitecturekotlin.data.rest

/**
 * Created by drake1804 on 5/19/17.
 */

class RestService(val restApi: RestApi) {

    fun getUsers() = restApi.getUsers()

}