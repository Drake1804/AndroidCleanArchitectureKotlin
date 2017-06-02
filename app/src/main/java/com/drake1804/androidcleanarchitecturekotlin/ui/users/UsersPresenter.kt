package com.drake1804.androidcleanarchitecturekotlin.ui.users

import com.drake1804.androidcleanarchitecturekotlin.business.interfaces.IUsersInteractor
import com.drake1804.androidcleanarchitecturekotlin.business.interfaces.IUsersPresenter
import com.drake1804.androidcleanarchitecturekotlin.utils.NetworkUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber

/**
 * Created by drake1804 on 5/20/17.
 */
class UsersPresenter(usersInteractor: IUsersInteractor) : IUsersPresenter {

    private var usersView: IUsersView? = null
    private val compositeDisposable = CompositeDisposable()
    private val disposable = usersInteractor.getUsers()
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext {
                if (NetworkUtil.isNetworkAvailable(usersView?.getContext()))
                    usersView?.showSnackbar("Check internet connection")
            }
            .subscribe(
                    { usersView?.showUsers(it) },
                    { Timber.tag(UsersPresenter::class.java.simpleName).e(it.message) },
                    { usersView?.dismissProgress() },
                    { usersView?.showProgress() })


    override fun bindView(usersView: IUsersView) {
        this.usersView = usersView
    }

    override fun unbindView() {
        compositeDisposable.clear()
        usersView = null
    }

    override fun loadUsers() {
        compositeDisposable.add(disposable)
    }
}