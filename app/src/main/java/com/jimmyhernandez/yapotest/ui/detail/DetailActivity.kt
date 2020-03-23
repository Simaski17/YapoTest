package com.jimmyhernandez.yapotest.ui.detail

import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ittalent.testitandroid.ui.common.Data
import com.ittalent.testitandroid.ui.common.DataState
import com.jimmyhernandez.domain.albums.Albums
import com.jimmyhernandez.domain.users.UserResponse
import com.jimmyhernandez.yapotest.R
import com.jimmyhernandez.yapotest.ui.albums.AlbumDetailActivity
import com.jimmyhernandez.yapotest.ui.common.*
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity(), ConnectivityReceiver.ConnectivityReceiverListener {

    companion object {
        const val ALBUM = "DetailActivity:album"
    }

    private lateinit var component: DetailActivityComponent
    private val viewModel: DetailViewModel by lazy { getViewModel { component.detailViewModel } }
    private lateinit var detailAdapter: DetailAdapter

    private var isConnectedNet: Boolean = false
    private var viewIfl: View? = null
    private var mNetworkReceiver = ConnectivityReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        component = app.component.plus(DetailActivityModule(intent.getIntExtra(ALBUM, -1)))

        viewModel.model.observe(this, Observer(::updateUi))
        viewModel.modelListAlbums.observe(this, Observer(::updateListAlbums))
        viewModel.findUser()

        rvAlbumList.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)

            detailAdapter = DetailAdapter() {
                startActivity<AlbumDetailActivity>{
                    putExtra(AlbumDetailActivity.ALBUMDETAIL, it.id)
                }
            }

            rvAlbumList.adapter = detailAdapter
        }

    }

    private fun updateUi(event: Data<UserResponse>?) {

        event.with {
            when (dataState) {
                DataState.LOADING -> {
                }
                DataState.SUCCESS -> {
                }
                DataState.ERROR -> {
                }
            }

            data.notNull {
                tvNameUserDetail.text = it.name
                tvMailUserDetail.text = it.email
                tvUserNameUserDetail.text = it.username

                tvAddressUserDetail.text = it.address.street
                tvCityUserDetail.text = it.address.city
                tvSuiteUserDetail.text = it.address.suite
                tvZipcodeUserDetail.text = it.address.zipcode

                tvNameCompanyUserDetail.text = it.company.name
                tvCatchPhraseUserDetail.text = it.company.catchPhrase

                tvPhoneUserDetail.text = it.phone
                tvWebsitwUserDetail.text = it.website

                viewModel.findAlbum()
            }
        }
    }

    private fun updateListAlbums(event: Data<List<Albums>>?) {
        event.with {
            when (dataState) {
                DataState.LOADING -> {
                    tvNotAlbumDetail.visibility = GONE
                    pbRecyclerAlbumsDetails.visibility = VISIBLE
                }
                DataState.SUCCESS -> {
                    tvNotAlbumDetail.visibility = GONE
                    pbRecyclerAlbumsDetails.visibility = GONE
                }
                DataState.ERROR -> {
                    pbRecyclerAlbumsDetails.visibility = GONE
                    if (isConnectedNet){
                        viewModel.getListAlbums()
                    } else {
                        tvNotAlbumDetail.visibility = VISIBLE
                        tvNotAlbumDetail.text = "No hay álbumes asociados a este usuario"
                    }
                }
            }

            data.notNull {
                var lista = ArrayList(it)
                detailAdapter.updateListSongs(lista)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        registerReceiver(
            mNetworkReceiver,
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )
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
                viewIfl = vsNetworkingNotAvailableDetail.inflate()
            } else if (viewIfl != null) {
                viewIfl!!.visibility = View.VISIBLE
            }
        } else {
            viewIfl?.let {
                viewIfl!!.visibility = View.GONE
            }
        }
    }

}
