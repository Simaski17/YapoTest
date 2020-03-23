package com.jimmyhernandez.yapotest.ui.albums

import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jimmyhernandez.domain.photos.Photos
import com.jimmyhernandez.yapotest.R
import com.jimmyhernandez.yapotest.ui.common.inflate
import com.jimmyhernandez.yapotest.ui.common.loadUrl
import kotlinx.android.synthetic.main.item_album_detail.view.*

class AlbumDetailAdapter (private val listener: (Photos) -> Unit) : RecyclerView.Adapter<AlbumDetailAdapter.ViewHolder>()  {

    private var listOfAlbumsDetail = arrayListOf<Photos>()

    fun updateListAlbumsDetail(albumsDetail: ArrayList<Photos>) {
        this.listOfAlbumsDetail.addAll(albumsDetail)
        notifyDataSetChanged()
        Log.e("LIST","COUNT "+this.listOfAlbumsDetail.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.item_album_detail, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return this.listOfAlbumsDetail.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val albumDetail = listOfAlbumsDetail[position]
        holder.bind(albumDetail)
        holder.itemView.setOnClickListener { listener(albumDetail) }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(albumDetail: Photos) {

            Log.e("IMG","IMG "+albumDetail.thumbnailUrl+".jpg")
            itemView.tvAlbumDetail.text = albumDetail.title
            itemView.ivAlbumDetail.loadUrl(albumDetail.thumbnailUrl+".jpg")
        }
    }

}