package com.chatting.firebasecommon

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface AuthPresenter {
    fun login(email: String, password: String): Single<User>
    fun signUp(displayName: String, email: String, password: String): Completable =
        Completable.complete()

    fun isLoggedIn(): Boolean = false

    fun getLoggedInUser(): Single<User>
}

class AuthPresenterImpl : AuthPresenter {

    override fun isLoggedIn(): Boolean = FirebaseAuth.getInstance().currentUser != null

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

    override fun signUp(displayName: String, email: String, password: String): Completable {
        return Completable.create { emitter ->
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        val firebaseUser = FirebaseAuth.getInstance().currentUser!!

                        firebaseUser.updateProfile(
                            UserProfileChangeRequest.Builder()
                                .setDisplayName(displayName)
                                .build()
                        ).addOnCompleteListener { updateProfileRequest ->
                            if (updateProfileRequest.isSuccessful) {
                                emitter.onComplete()
                            } else {
                                emitter.onError(it.exception)
                            }
                        }
                    } else {
                        emitter.onError(it.exception)
                    }
                }
        }
    }
}

class NotLoggedIn : Throwable()
class NotSignUp : Throwable()