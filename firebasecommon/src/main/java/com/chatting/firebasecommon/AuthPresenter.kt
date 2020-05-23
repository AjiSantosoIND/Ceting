package com.chatting.firebasecommon

import com.google.firebase.auth.FirebaseAuth
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface AuthPresenter {
    fun login(email: String, password: String): Single<User>
    fun signUp(user: User): Completable = Completable.complete()

    fun isLoggedIn(): Boolean = false

    fun getLoggedInUser(): Single<User>
}

class AuthPresenterImpl : AuthPresenter {
    override fun login(email: String, password: String): Single<User> {
        return Single.create { emitter ->
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        val firebaseUser = FirebaseAuth.getInstance().currentUser!!
                        emitter.onSuccess(User(firebaseUser))
                    } else {
                        emitter.onError(NotLoggedIn())
                    }
                }
        }
    }

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