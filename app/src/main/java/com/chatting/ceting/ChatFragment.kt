package com.chatting.ceting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class ChatFragment : BaseFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_chat, container, false)
    }
}