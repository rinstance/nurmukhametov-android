package com.example.myapplication.auth.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.App
import com.example.myapplication.Constants
import com.example.myapplication.R
import com.example.myapplication.auth.AuthActivity
import com.example.myapplication.auth.AuthPresenter
import kotlinx.android.synthetic.main.fragment_registration.*
import javax.inject.Inject

class RegistrationFragment : Fragment() {
    @Inject lateinit var presenter: AuthPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setDI()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_registration, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        login_button?.setOnClickListener { login() }
        login_text?.setOnClickListener { loginFragment() }
    }

    private fun loginFragment() {
        presenter.openLoginFragment()
    }

    private fun login() {
        (activity as AuthActivity).setDataAction(object : AuthActivity.DataAction {
            override fun getData(): Bundle {
                return Bundle().apply {
                    putString(Constants.INTENT_LOGIN, login_edit.text.toString())
                    putString(Constants.INTENT_PASSWORD, password_edit.text.toString())
                }
            }
        })
        presenter.openMainActivity()
    }

    private fun setDI() {
        App.appComponent
            .authComponent()
            .loginComponent()
            .injectReg(this)
    }
}