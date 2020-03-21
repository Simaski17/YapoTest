package com.jimmyhernandez.yapotest.ui.user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.ittalent.testitandroid.ui.common.Data
import com.ittalent.testitandroid.ui.common.DataState
import com.jimmyhernandez.domain.users.UserResponse
import com.jimmyhernandez.yapotest.R
import com.jimmyhernandez.yapotest.ui.common.app
import com.jimmyhernandez.yapotest.ui.common.getViewModel
import com.jimmyhernandez.yapotest.ui.common.notNull
import com.jimmyhernandez.yapotest.ui.common.with

class MainActivity : AppCompatActivity() {

    private lateinit var component: MainActivityComponent
    private val viewModel: UsersViewModel by lazy { getViewModel { component.usersViewModel } }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        component = app.component.plus(MainActivityModule())
        viewModel.model.observe(this, Observer(::updateUi))
        viewModel.model.observe(this, Observer(::showData))

//        viewModel.getListUsers()
        viewModel.getAllUsers()

    }

    private fun updateUi(event: Data<ArrayList<UserResponse>>?) {

        event.with {
            when (dataState) {
                DataState.LOADING -> {
//                    if (ifLoading) progress.visibility = View.VISIBLE else progressNextItems.visibility =
//                        View.VISIBLE
                }
                DataState.SUCCESS -> {
//                    progress.visibility = View.GONE
//                    progressNextItems.visibility = View.GONE
//                    ifLoading = true
                }
                DataState.ERROR -> {
//                    progress.visibility = View.GONE
//                    progressNextItems.visibility = View.GONE
//                    ifLoading = true
                }
            }

            data.notNull {
//                itunesAdapter.updateListSongs(it)
            }
        }


    }


    private fun showData(event: Data<ArrayList<UserResponse>>?) {
        event.with {
            when (dataState) {
                DataState.LOADING -> {
//                    if (ifLoading) progress.visibility = View.VISIBLE else progressNextItems.visibility =
//                        View.VISIBLE
                }
                DataState.SUCCESS -> {
//                    progress.visibility = View.GONE
//                    progressNextItems.visibility = View.GONE
//                    ifLoading = true
                }
                DataState.ERROR -> {
//                    progress.visibility = View.GONE
//                    progressNextItems.visibility = View.GONE
//                    ifLoading = true
                }
            }

            data.notNull {
//                itunesAdapter.updateListSongs(it)
            }
        }
    }



}
