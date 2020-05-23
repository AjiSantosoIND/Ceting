package com.chatting.ceting.login

import android.os.Bundle
import com.chatting.ceting.BaseActivity
import com.chatting.ceting.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        Picasso.get().load(R.drawable.bg_login).into(iv_background)
    }
}