package com.chatting.ceting

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.chatting.ceting.componentviews.LoginBottomDialog

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dialog = LoginBottomDialog()
        dialog.show(supportFragmentManager, "")
    }
}
