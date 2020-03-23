package com.jimmyhernandez.yapotest.ui.detail

import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jimmyhernandez.domain.albums.Albums
import com.jimmyhernandez.yapotest.R
import com.jimmyhernandez.yapotest.ui.common.inflate
import kotlinx.android.synthetic.main.item_albums_list.view.*

class DetailAdapter(private val listener: (Albums) -> Unit) : RecyclerView.Adapter<DetailAdapter.ViewHolder>()  {

    private var listOfAlbums = arrayListOf<Albums>()

    fun updateListSongs(albums: ArrayList<Albums>) {
        this.listOfAlbums.addAll(albums)
        notifyDataSetChanged()
        Log.e("LIST","COUNT "+this.listOfAlbums.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailAdapter.ViewHolder {
        val view = parent.inflate(R.layout.item_albums_list, false)
        return DetailAdapter.ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return this.listOfAlbums.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val album = listOfAlbums[position]
        holder.bind(album)
        holder.itemView.setOnClickListener { listener(album) }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(album: Albums) {
            itemView.tvNameAlbumDetail.text = album.title
        }
    }

}