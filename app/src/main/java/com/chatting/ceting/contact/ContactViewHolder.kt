package com.chatting.ceting.contact

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.chatting.firebasecommon.User
import kotlinx.android.synthetic.main.view_holder_contact.view.*

class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(user: User) {
        itemView.tv_contact_name.text = user.displayName()
    }
}