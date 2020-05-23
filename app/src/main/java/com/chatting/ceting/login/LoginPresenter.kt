package com.chatting.ceting.login

import com.chatting.firebasecommon.AuthPresenter
import com.chatting.firebasecommon.User
import io.reactivex.rxjava3.core.Single

interface LoginPresenter {
    fun login(email: String, password: String): Single<User>
}

class LoginPresenterImpl(
    private val authPresenter: AuthPresenter
) : LoginPresenter {
    override fun login(email: String, password: String): Single<User> {
        return authPresenter.login(email, password)
    }
}