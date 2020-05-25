package com.chatting.ceting.roommessage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chatting.ceting.R
import com.chatting.ceting.model.RoomMessage

class RoomMessageAdapter : RecyclerView.Adapter<RoomMessageViewHolder>() {
    var dataList: MutableList<RoomMessage> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomMessageViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_room_message, parent, false)

        return RoomMessageViewHolder(itemView)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: RoomMessageViewHolder, position: Int) =
        holder.bind(dataList[position])
}