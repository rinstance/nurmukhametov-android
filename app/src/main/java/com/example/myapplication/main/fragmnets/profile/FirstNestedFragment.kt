package com.example.myapplication.main.fragmnets.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.App
import com.example.myapplication.R
import com.example.myapplication.main.MainPresenter
import kotlinx.android.synthetic.main.fragment_first_nested.*
import javax.inject.Inject

class FirstNestedFragment : Fragment() {
    @Inject lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setDI()
    }

    private fun setDI() {
        App.appComponent
            .mainComponent()
            .mainFragmentsComponent()
            .injectFirst(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_first_nested, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        second_button?.setOnClickListener { openSecond() }
    }

    private fun openSecond() {
        presenter.openSecond()
    }
}