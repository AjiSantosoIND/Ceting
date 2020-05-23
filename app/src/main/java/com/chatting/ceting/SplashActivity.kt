package com.chatting.ceting

import android.os.Bundle
import com.chatting.ceting.login.LoginActivity
import com.chatting.firebasecommon.AuthPresenterImpl
import org.jetbrains.anko.startActivity

class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val presenter = AuthPresenterImpl()
        if (presenter.isLoggedIn()) {
            startActivity<MainActivity>()
        } else {
            startActivity<LoginActivity>()
        }
        
        finish()
    }
}