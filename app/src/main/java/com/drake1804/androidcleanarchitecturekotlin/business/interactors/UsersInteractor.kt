package com.drake1804.androidcleanarchitecturekotlin.business.interactors

import com.drake1804.androidcleanarchitecturekotlin.business.EmptyException
import com.drake1804.androidcleanarchitecturekotlin.business.interfaces.IUsersInteractor
import com.drake1804.androidcleanarchitecturekotlin.data.rest.UserModel
import com.drake1804.androidcleanarchitecturekotlin.business.interfaces.IUsersRepository
import io.reactivex.Observable

/**
 * Created by drake1804 on 5/19/17.
 */

class UsersInteractor(val userRepository: IUsersRepository) : IUsersInteractor {

    override fun getUsers(): Observable<List<UserModel>> {
        return userRepository.getUsers()
                .flatMap {userModels ->
                    if (userModels.isNotEmpty())
                        Observable.just(userModels)
                    else
                        Observable.error(EmptyException("Empty"))}
    }
}