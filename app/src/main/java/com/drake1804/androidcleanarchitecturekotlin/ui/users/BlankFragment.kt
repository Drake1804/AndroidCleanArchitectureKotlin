package com.drake1804.androidcleanarchitecturekotlin.ui.users


import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.drake1804.androidcleanarchitecturekotlin.ACAKApplication
import com.drake1804.androidcleanarchitecturekotlin.R
import com.drake1804.androidcleanarchitecturekotlin.business.interfaces.IUsersPresenter
import com.drake1804.androidcleanarchitecturekotlin.data.rest.UserModel
import kotlinx.android.synthetic.main.fragment_blank.*
import javax.inject.Inject

class BlankFragment : Fragment(), IUsersView, SwipeRefreshLayout.OnRefreshListener {

    @Inject
    lateinit var presenter: IUsersPresenter

    private lateinit var usersAdapter: UsersAdapter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view: View = inflater!!.inflate(R.layout.fragment_blank, container, false)
        ACAKApplication.get(activity).plusUsersComponent().inject(this)
        presenter.bindView(this)

        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        swipeRefreshLayout.setOnRefreshListener { this }
        recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        usersAdapter = UsersAdapter()
        recyclerView.adapter = usersAdapter
    }

    override fun onResume() {
        super.onResume()
        presenter.loadUsers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.unbindView()
    }

    override fun onRefresh() = presenter.loadUsers()


    override fun showProgress() {
        swipeRefreshLayout.isRefreshing = true
    }


    override fun showError(text: String) {
    }

    override fun dismissProgress() {
        swipeRefreshLayout.isRefreshing = false
    }

    override fun showToast(text: String) = Toast.makeText(activity, text, Toast.LENGTH_LONG).show()


    override fun showSnackbar(text: String) = Snackbar.make(activity.findViewById(android.R.id.content), text, Snackbar.LENGTH_LONG).show()


    override fun showUsers(users: List<UserModel>) = usersAdapter.setItems(users)
}
