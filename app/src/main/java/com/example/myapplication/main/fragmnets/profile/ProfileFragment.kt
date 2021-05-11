package com.example.myapplication.main.fragmnets.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.App
import com.example.myapplication.Constants
import com.example.myapplication.R
import com.example.myapplication.main.MainPresenter
import kotlinx.android.synthetic.main.fragment_profile.*
import javax.inject.Inject

class ProfileFragment : Fragment() {
    @Inject lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setDI()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        first_button?.setOnClickListener { toFirstFragment() }
        setView()
    }

    private fun setView() {
        val bundle = activity?.intent?.extras
        text_login?.text = bundle?.getString(Constants.INTENT_LOGIN)
        text_password?.text = bundle?.getString(Constants.INTENT_PASSWORD)
    }

    private fun toFirstFragment() {
        presenter.openFirst()
    }

    private fun setDI() {
        App.appComponent
            .mainComponent()
            .mainFragmentsComponent()
            .injectProfile(this)
    }
}