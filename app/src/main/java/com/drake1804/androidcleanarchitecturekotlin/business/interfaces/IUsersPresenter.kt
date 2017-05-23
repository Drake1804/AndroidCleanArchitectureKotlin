package com.drake1804.androidcleanarchitecturekotlin.business.interfaces

import com.drake1804.androidcleanarchitecturekotlin.ui.users.IUsersView

/**
 * Created by drake1804 on 5/20/17.
 */
interface IUsersPresenter {

    fun bindView(usersView: IUsersView)
    fun unbindView()
    fun loadUsers()
}