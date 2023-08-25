package com.example.chattingappsbagas.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chattingappsbagas.R
import com.example.chattingappsbagas.model.User
import de.hdodenhof.circleimageview.CircleImageView

class UserAdapter(private val context: Context, private val userList:
ArrayList<User>) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtUserName: TextView = view.findViewById(R.id.userName)
        val imgUser: CircleImageView = view.findViewById(R.id.userImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: UserAdapter.ViewHolder, position: Int) {
        val user = userList[position]
        holder.txtUserName.text = user.userName
        Glide.with(context).load(user.profileImage).placeholder(R.drawable.profile_image).into(holder.imgUser)
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}