package com.chatting.ceting.contact

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chatting.ceting.R
import com.chatting.firebasecommon.User

class ContactAdapter : RecyclerView.Adapter<ContactViewHolder>() {
    var dataList: MutableList<User> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        return LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_contact, parent, false).run {
                ContactViewHolder(this)
            }
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) =
        holder.bind(dataList[position])
}