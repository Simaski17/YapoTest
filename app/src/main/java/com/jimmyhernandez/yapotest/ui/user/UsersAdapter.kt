package com.jimmyhernandez.yapotest.ui.user

import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jimmyhernandez.domain.users.UserResponse
import com.jimmyhernandez.yapotest.R
import com.jimmyhernandez.yapotest.ui.common.inflate
import kotlinx.android.synthetic.main.item_users_view.view.*

class UsersAdapter(private val listener: (UserResponse) -> Unit) :
    RecyclerView.Adapter<UsersAdapter.ViewHolder>() {


    private var listOfUsers = arrayListOf<UserResponse>()

    fun updateUsersList(users: ArrayList<UserResponse>) {
        this.listOfUsers.addAll(users)
        notifyDataSetChanged()
        Log.e("LIST", "COUNT " + this.listOfUsers.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.item_users_view, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return this.listOfUsers.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = listOfUsers[position]
        holder.bind(user)
        holder.itemView.setOnClickListener { listener(user) }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(user: UserResponse) {
            itemView.tvNameUsers.text = user.name
            itemView.tvMailUsers.text = user.email
            Log.e("LIST", "COUNT " +user.email)
        }
    }

}