package com.drake1804.androidcleanarchitecturekotlin.business.interactors

import com.drake1804.androidcleanarchitecturekotlin.business.EmptyException
import com.drake1804.androidcleanarchitecturekotlin.business.interfaces.IUsersInteractor
import com.drake1804.androidcleanarchitecturekotlin.business.interfaces.IUsersRepository
import com.drake1804.androidcleanarchitecturekotlin.data.rest.UserModel
import io.reactivex.Observable

/**
 * Created by drake1804 on 5/19/17.
 */

class UsersInteractor(val userRepository: IUsersRepository) : IUsersInteractor {

    override fun getUsers(): Observable<List<UserModel>> =
            userRepository.getUsers()
                    .flatMap { if (it.isNotEmpty()) Observable.just(it) else Observable.error(EmptyException("Empty"))}

}