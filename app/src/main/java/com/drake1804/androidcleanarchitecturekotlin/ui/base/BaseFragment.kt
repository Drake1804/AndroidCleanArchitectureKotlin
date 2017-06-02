package com.drake1804.androidcleanarchitecturekotlin.ui.base

import android.content.Context
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by Pavel.Shkaran on 5/30/2017.
 */
open class BaseFragment : Fragment() {

    fun Context.inflate(res: Int, parent: ViewGroup? = null) : View {
        return LayoutInflater.from(this).inflate(res, parent, false)
    }

    fun View.visible() {
        visibility = View.VISIBLE
    }
    fun View.gone() {
        visibility = View.GONE
    }

}