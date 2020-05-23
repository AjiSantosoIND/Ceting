package com.chatting.ceting.componentviews

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chatting.ceting.MainActivity
import com.chatting.ceting.R
import com.chatting.firebasecommon.AuthPresenterImpl
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.bottom_dialog_login.*
import org.jetbrains.anko.support.v4.alert
import org.jetbrains.anko.support.v4.indeterminateProgressDialog
import org.jetbrains.anko.support.v4.startActivity
import santoso.adjie.rxjavasimplify.subscribeToMainThread

class LoginBottomDialog : BottomSheetDialogFragment() {
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
        return inflater.inflate(R.layout.bottom_dialog_login, container, false)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState).apply {
            this.window?.setDimAmount(0F)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val authPresenter = AuthPresenterImpl()

        btn_sign_in.setOnClickListener {
            val email = et_email.text.toString()
            val password = et_password.text.toString()

            val loading =
                indeterminateProgressDialog(title = R.string.loading, message = R.string.sign_in_in)
            loading.show()

            authPresenter.login(email, password)
                .subscribeToMainThread()
                .subscribe({
                    loading.dismiss()

                    alert(title = R.string.login_success, message = R.string.login_success) {
                        positiveButton(R.string.open_chat) {
                            it.dismiss()

                            this@LoginBottomDialog.activity?.finish()
                            this@LoginBottomDialog.dismiss()

                            startActivity<MainActivity>()
                        }
                    }.show()
                }, { it ->
                    it.printStackTrace()

                    loading.dismiss()

                    alert(title = R.string.login_failed, message = R.string.login_failed) {
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