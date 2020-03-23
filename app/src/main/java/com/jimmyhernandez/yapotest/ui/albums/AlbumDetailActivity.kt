package com.jimmyhernandez.yapotest.ui.albums

import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ittalent.testitandroid.ui.common.Data
import com.ittalent.testitandroid.ui.common.DataState
import com.jimmyhernandez.domain.photos.Photos
import com.jimmyhernandez.yapotest.R
import com.jimmyhernandez.yapotest.ui.common.*
import com.jimmyhernandez.yapotest.ui.detail.DetailActivity
import com.jimmyhernandez.yapotest.ui.detail.DetailActivityModule
import kotlinx.android.synthetic.main.activity_album_detail.*

class AlbumDetailActivity : AppCompatActivity(), ConnectivityReceiver.ConnectivityReceiverListener {

    companion object {
        const val ALBUMDETAIL = "AlbumDetailActivity:albumDetail"
    }

    private lateinit var component: AlbumsActivityComponent
    private val viewModel: AlbumDetailViewModel by lazy { getViewModel { component.albumDetailViewModel } }
    private lateinit var albumDetailAdapter: AlbumDetailAdapter

    private var isConnectedNet: Boolean = false
    private var viewIfl: View? = null
    private var mNetworkReceiver = ConnectivityReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_detail)

        component = app.component.plus(AlbumsActivityModule(intent.getIntExtra(ALBUMDETAIL, -1)))

        viewModel.model.observe(this, Observer(::updateListAlbums))
        viewModel.findAlbumDetail()

        rvAlbumDetail.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)

            albumDetailAdapter = AlbumDetailAdapter() {

            }
            rvAlbumDetail.adapter = albumDetailAdapter

        }

    }

    private fun updateListAlbums(event: Data<List<Photos>>?) {
        event.with {
            when (dataState) {
                DataState.LOADING -> {
//                    tvNotAlbumDetail.visibility = GONE
//                    pbRecyclerAlbumsDetails.visibility = VISIBLE
                }
                DataState.SUCCESS -> {
//                    tvNotAlbumDetail.visibility = GONE
//                    pbRecyclerAlbumsDetails.visibility = GONE
                }
                DataState.ERROR -> {
//                    pbRecyclerAlbumsDetails.visibility = GONE
                    if (isConnectedNet) {
                        viewModel.getListAlbumsDetail()
                    } else {
//                        tvNotAlbumDetail.visibility = VISIBLE
//                        tvNotAlbumDetail.text = "No hay álbumes asociados a este usuario"
                    }
                }
            }

            data.notNull {
                var lista = ArrayList(it)
                albumDetailAdapter.updateListAlbumsDetail(lista)
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
                viewIfl = vsNetworkingNotAvailableAlbumDetail.inflate()
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
