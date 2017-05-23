package com.drake1804.androidcleanarchitecturekotlin.ui.users

import com.drake1804.androidcleanarchitecturekotlin.business.interfaces.IUsersInteractor
import com.drake1804.androidcleanarchitecturekotlin.business.interfaces.IUsersPresenter
import com.drake1804.androidcleanarchitecturekotlin.utils.NetworkUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by drake1804 on 5/20/17.
 */
class UsersPresenter(val usersInteractor: IUsersInteractor) : IUsersPresenter {

    var usersView: IUsersView? = null
    val compositeDisposable: CompositeDisposable = CompositeDisposable()


    override fun bindView(usersView: IUsersView) {
        this.usersView = usersView
    }

    override fun unbindView() {
        compositeDisposable.clear()
        usersView = null
    }

    override fun loadUsers() {
        usersView?.showProgress()
        val disposable: Disposable = usersInteractor.getUsers()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext({
                    if (NetworkUtil.isNetworkAvailable(usersView?.getContext()))
                        usersView?.showSnackbar("Check internet connection")
                })
                .subscribe({ users -> usersView?.showUsers(users) })
        compositeDisposable.add(disposable)
    }
}