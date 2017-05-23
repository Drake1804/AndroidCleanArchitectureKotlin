package com.drake1804.androidcleanarchitecturekotlin.ui.base

/**
 * Created by drake1804 on 5/20/17.
 */
interface IBaseView {
    fun showProgress()
    fun showError(text: String)
    fun dismissProgress()
    fun showToast(text: String)
    fun showSnackbar(text: String)
}