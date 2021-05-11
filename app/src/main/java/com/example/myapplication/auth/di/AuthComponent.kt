package com.example.myapplication.auth.di

import com.example.myapplication.auth.AuthActivity
import dagger.Component
import dagger.Subcomponent

@Subcomponent
interface AuthComponent {
    fun inject(activity: AuthActivity)

    fun loginComponent() : LoginComponent
}