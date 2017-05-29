package com.drake1804.androidcleanarchitecturekotlin.ui.users

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.drake1804.androidcleanarchitecturekotlin.R
import com.drake1804.androidcleanarchitecturekotlin.data.rest.UserModel
import kotlinx.android.synthetic.main.user_item.view.*

/**
 * Created by drake1804 on 5/21/17.
 */
class UsersAdapter : RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    private var items: MutableList<UserModel>? = null

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int) =
            ViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.user_item, parent, false))

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.name?.text = items?.get(position)?.name
        holder?.username?.text = items?.get(position)?.username
        holder?.email?.text = items?.get(position)?.email
    }

    override fun getItemCount(): Int {
        if(items == null) return 0
        return items?.size!!
    }

    fun setItems(users: List<UserModel>) {
        if(items == null) {
            items = mutableListOf()
        }
        items?.clear()
        items?.addAll(users)
        notifyDataSetChanged()
    }


    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        var name = itemView?.name
        var username = itemView?.username
        var email = itemView?.email
    }
}