package com.jimmyhernandez.yapotest.ui.main

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.jimmyhernandez.yapotest.R
import com.jimmyhernandez.yapotest.ui.common.ConnectivityReceiver
import com.jimmyhernandez.yapotest.ui.common.app
import com.jimmyhernandez.yapotest.ui.common.getViewModel
import com.jimmyhernandez.yapotest.ui.common.startActivity
import com.jimmyhernandez.yapotest.ui.user.UsersActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), View.OnClickListener, ConnectivityReceiver.ConnectivityReceiverListener{

    private lateinit var component: MainActivityComponent
    private val viewModel: MainViewModel by lazy { getViewModel { component.mainViewModel } }
    private var viewIfl: View? = null
    private var mNetworkReceiver =
        ConnectivityReceiver()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        component = app.component.plus(MainActivityModule())

        tvUsersListMain.setOnClickListener(this)
        tvUsersFavoriteListMain.setOnClickListener(this)

    }

    override fun onPause() {
        super.onPause()
        viewIfl?.let {
            viewIfl!!.visibility = View.GONE
        }
        unregisterReceiver(mNetworkReceiver)
    }

    override fun onResume() {
        super.onResume()
        registerReceiver(mNetworkReceiver,
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
        ConnectivityReceiver.connectivityReceiverListener = this
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.tvUsersListMain -> {
                    startActivity<UsersActivity> {
                        putExtra(UsersActivity.FAVORITE, false)
                    }
            }
            R.id.tvUsersFavoriteListMain -> {
                    startActivity<UsersActivity>{
                        putExtra(UsersActivity.FAVORITE, true)
                    }
            }
        }
    }

    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        if (!isConnected) {
            if (viewIfl == null) {
                viewIfl = View(applicationContext)
                viewIfl = vsNetworkingNotAvailable.inflate()
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
