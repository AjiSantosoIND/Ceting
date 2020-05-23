package com.chatting.ceting

import android.os.Bundle
import com.chatting.ceting.login.LoginActivity
import org.jetbrains.anko.startActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startActivity<LoginActivity>()
    }
}
