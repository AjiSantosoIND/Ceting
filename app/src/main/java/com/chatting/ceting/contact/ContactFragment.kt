package com.chatting.ceting.contact

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chatting.ceting.BaseFragment
import com.chatting.ceting.R
import com.chatting.firebasecommon.User
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_contact.*

class ContactFragment : BaseFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_contact, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val adapter = ContactAdapter()
        adapter.dataList = mutableListOf(
            User(FirebaseAuth.getInstance().currentUser!!)
        )

        rv_contact.layoutManager = LinearLayoutManager(
            requireContext(),
            RecyclerView.VERTICAL, false
        )

        rv_contact.adapter = adapter

        view_switcher.showNext()
    }
}