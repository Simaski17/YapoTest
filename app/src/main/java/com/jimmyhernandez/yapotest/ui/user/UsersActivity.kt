package com.jimmyhernandez.yapotest.ui.user

import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ittalent.testitandroid.ui.common.Data
import com.ittalent.testitandroid.ui.common.DataState
import com.jimmyhernandez.domain.users.UserResponse
import com.jimmyhernandez.yapotest.R
import com.jimmyhernandez.yapotest.ui.common.ConnectivityReceiver
import com.jimmyhernandez.yapotest.ui.common.*
import com.jimmyhernandez.yapotest.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.activity_users.*

class UsersActivity : AppCompatActivity(), ConnectivityReceiver.ConnectivityReceiverListener {

    companion object {
        const val FAVORITE = "UsersActivity:favorite"
    }

    private lateinit var component: UsersActivityComponent
    private val viewModel: UsersViewModel by lazy { getViewModel { component.usersViewModel } }
    private lateinit var usersAdapter: UsersAdapter
    private var favorite: Boolean = false
    private var isConnectedNet: Boolean = false
    private var viewIfl: View? = null
    private var mNetworkReceiver = ConnectivityReceiver()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)

        component = app.component.plus(UsersActivityModule())

        viewModel.model.observe(this, Observer(::updateUi))
        viewModel.modelCount.observe(this, Observer(::getCount))

        favorite = intent.getBooleanExtra(FAVORITE, false)
        if (favorite) {
            tvTitleUsersList.text = getString(R.string.title_users_favorite__list)
        } else {
            tvTitleUsersList.text = getString(R.string.title_users_list)
        }

        rvUsersList.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)

            usersAdapter = UsersAdapter() {
                startActivity<DetailActivity>{
                    putExtra(DetailActivity.ALBUM, it.id)
                }
            }
            rvUsersList.adapter = usersAdapter

        }

        viewModel.getCount()

    }


    private fun updateUi(event: Data<List<UserResponse>>?) {

        event.with {
            when (dataState) {
                DataState.LOADING -> {
                    pbUsersList.visibility = VISIBLE
                }
                DataState.SUCCESS -> {
                    pbUsersList.visibility = GONE
                    rvUsersList.visibility = VISIBLE
                }
                DataState.ERROR -> {
                }
            }

            data.notNull {
                var lista = ArrayList(it)
                usersAdapter.updateUsersList(lista)
            }
        }


    }


//    private fun showData(event: Data<List<UserResponse>>?) {
//        event.with {
//            when (dataState) {
//                DataState.LOADING -> {
//                    pbUsersList.visibility = VISIBLE
//                }
//                DataState.SUCCESS -> {
//                    pbUsersList.visibility = GONE
//                    rvUsersList.visibility = VISIBLE
//                }
//                DataState.ERROR -> {
//                }
//            }
//
//            data.notNull {
//                var lista = ArrayList(it)
//                usersAdapter.updateUsersList(lista)
//            }
//        }
//    }

    private fun getCount(event: Data<Boolean>) {
        event.with {
            when (dataState) {
                DataState.LOADING -> pbUsersList.visibility = VISIBLE
                DataState.SUCCESS -> pbUsersList.visibility = GONE
                DataState.ERROR -> {
                }
            }

            data.notNull {
                if (it) {//0
                    if (favorite) {
                        tvNotUserFavorite.text = getString(R.string.not_favorite_users)
                    } else {
                        if(isConnectedNet) {
                            tvNotUserFavorite.visibility = GONE
                            viewModel.getListUsers()
                        }else {
                            tvNotUserFavorite.text = getString(R.string.not_users)
                        }
                    }
                } else {//>0
                    if (favorite) {
                        tvNotUserFavorite.visibility = VISIBLE
                    } else {
                        tvNotUserFavorite.visibility = GONE
                        viewModel.getAllUsers()
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        registerReceiver(mNetworkReceiver,
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
        ConnectivityReceiver.connectivityReceiverListener = this
    }

    override fun onPause() {
        super.onPause()
        viewIfl?.let {
            viewIfl!!.visibility = View.GONE
        }
        unregisterReceiver(mNetworkReceiver)
    }

    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        isConnectedNet = isConnected
        if (!isConnected) {
            if (viewIfl == null) {
                viewIfl = View(applicationContext)
                viewIfl = vsNetworkingNotAvailableUsers.inflate()
            } else if (viewIfl != null){
                viewIfl!!.visibility = View.VISIBLE
            }
        } else {
            viewIfl?.let {
                viewIfl!!.visibility = View.GONE
            }
        }
    }

}
