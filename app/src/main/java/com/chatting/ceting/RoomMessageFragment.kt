package com.chatting.ceting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chatting.ceting.model.RoomMessage
import com.chatting.ceting.roommessage.RoomMessageAdapter
import kotlinx.android.synthetic.main.fragment_room_message.*
import java.util.*

class RoomMessageFragment : BaseFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_room_message, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        view_switcher.showNext()

        val adapter = RoomMessageAdapter()
        adapter.dataList = mutableListOf(
            RoomMessage("a", "b", Date(), false),
            RoomMessage("a", "b", Date(), false),
            RoomMessage("a", "b", Date(), false),
            RoomMessage("a", "b", Date(), false),
            RoomMessage("a", "b", Date(), false)
        )

        rv_room_message.layoutManager = LinearLayoutManager(
            requireContext(), RecyclerView.VERTICAL,
            false
        )

        rv_room_message.adapter = adapter
    }
}