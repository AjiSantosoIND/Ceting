package com.chatting.ceting.componentviews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chatting.ceting.MainActivity
import com.chatting.ceting.R
import com.chatting.firebasecommon.AuthPresenterImpl
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.bottom_dialog_register.*
import org.jetbrains.anko.support.v4.alert
import org.jetbrains.anko.support.v4.indeterminateProgressDialog
import org.jetbrains.anko.support.v4.startActivity
import santoso.adjie.rxjavasimplify.subscribeToMainThread

class RegisterBottomDialog : BottomSheetDialogFragment() {
    private var compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.MyBottomSheetDialogTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_dialog_register, container, false)
    }

    private fun formValidation(): Boolean {
        val displayName = et_display_name.text.toString()
        val email = et_email.text.toString()
        val password = et_password.text.toString()
        val repeatPassword = et_confirm_password.text.toString()

        if (displayName.isEmpty()) return false
        if (email.isEmpty()) return false
        if (password.isEmpty()) return false
        if (repeatPassword.isEmpty()) return false

        if (password != repeatPassword) return false

        return true
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val authPresenter = AuthPresenterImpl()
        btn_sign_up.setOnClickListener {
            if (!formValidation()) {
                alert(title = R.string.form_not_valid, message = R.string.form_not_valid) {
                    positiveButton(R.string.ok) {
                        it.dismiss()
                    }
                }.show()

                return@setOnClickListener
            }

            val displayName = et_display_name.text.toString()
            val email = et_email.text.toString()
            val password = et_password.text.toString()

            val loading =
                indeterminateProgressDialog(title = R.string.loading, message = R.string.sign_up_in)
            loading.show()

            authPresenter.signUp(displayName, email, password)
                .subscribeToMainThread()
                .subscribe({
                    loading.dismiss()

                    alert(title = R.string.register_success, message = R.string.register_success) {
                        positiveButton(R.string.open_chat) {
                            it.dismiss()

                            this@RegisterBottomDialog.activity?.finish()
                            this@RegisterBottomDialog.dismiss()

                            startActivity<MainActivity>()
                        }
                    }.show()
                }, {
                    it.printStackTrace()

                    loading.dismiss()

                    alert(
                        title = getString(R.string.register_failed),
                        message = it.message ?: "unknown"
                    ) {
                        positiveButton(R.string.ok) {
                            it.dismiss()
                        }
                    }.show()
                }).let { compositeDisposable.add(it) }
        }
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }
}