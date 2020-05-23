package com.chatting.ceting

import android.os.Bundle
import com.chatting.ceting.login.LoginActivity
import org.jetbrains.anko.startActivity

class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity<LoginActivity>()
        finish()
    }
}