package com.example.myapplication.auth.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.myapplication.App
import com.example.myapplication.R
import com.example.myapplication.auth.AuthPresenter
import kotlinx.android.synthetic.main.fragment_login.*
import javax.inject.Inject

class LoginFragment : Fragment() {
    @Inject lateinit var presenter: AuthPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setDI()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_login, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        login_button?.setOnClickListener { login() }
    }

    private fun login() {
        if (!presenter.openMainActivity(login_edit?.text.toString(), password_edit?.text.toString())) {
            Toast.makeText(context, R.string.invalid, Toast.LENGTH_SHORT).show()
        }
    }

    private fun setDI() {
        App.appComponent
            .authComponent()
            .loginComponent()
            .injectLogin(this)
    }
}