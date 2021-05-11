package com.example.myapplication.auth.di

import com.example.myapplication.auth.fragments.LoginFragment
import com.example.myapplication.auth.fragments.RegistrationFragment
import dagger.Subcomponent

@Subcomponent(modules = [LoginModule::class])
interface LoginComponent {
    fun injectLogin(fragment: LoginFragment)
    fun injectReg(fragment: RegistrationFragment)
}