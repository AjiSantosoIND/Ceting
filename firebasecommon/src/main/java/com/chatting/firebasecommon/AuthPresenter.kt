package com.chatting.firebasecommon

import com.google.firebase.auth.FirebaseAuth
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface AuthPresenter {
    fun login(user: User): Completable = Completable.complete()
    fun signUp(user: User): Completable = Completable.complete()

    fun isLoggedIn(): Boolean = false

    fun getLoggedInUser(): Single<User>
}

class AuthPresenterImpl : AuthPresenter {
    override fun getLoggedInUser(): Single<User> {
        return Single.create { emitter ->
            FirebaseAuth.getInstance().currentUser?.let {
                emitter.onSuccess(User(it))
            } ?: run {
                emitter.onError(NotLoggedIn())
            }
        }
    }
}

class NotLoggedIn : Throwable()