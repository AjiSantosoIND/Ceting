package com.chatting.ceting.login

import android.os.Bundle
import android.view.View
import com.chatting.ceting.BaseActivity
import com.chatting.ceting.R
import com.chatting.ceting.componentviews.LoginBottomDialog
import com.chatting.firebasecommon.AuthPresenterImpl
import com.squareup.picasso.Picasso
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {
    private val onClickListener = View.OnClickListener {
        when (it.id) {
            R.id.btn_login -> {
                LoginBottomDialog().show(supportFragmentManager, "")
            }
        }
    }

    private lateinit var presenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        Picasso.get().load(R.drawable.bg_login).into(iv_background)

        btn_login.setOnClickListener(onClickListener)

        presenter = LoginPresenterImpl(AuthPresenterImpl())
    }
}