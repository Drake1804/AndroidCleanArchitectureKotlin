package com.drake1804.androidcleanarchitecturekotlin.ui.users

import android.content.Context
import com.drake1804.androidcleanarchitecturekotlin.data.rest.UserModel
import com.drake1804.androidcleanarchitecturekotlin.ui.base.IBaseView

/**
 * Created by drake1804 on 5/20/17.
 */
interface IUsersView: IBaseView {

    fun showUsers(users: List<UserModel>)
    fun getContext(): Context
}