package com.example.myapplication.auth.di

import com.example.myapplication.auth.AuthPresenter
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Router

@Module
class LoginModule {
    @Provides
    fun providePresenter(router: Router) = AuthPresenter(router)
}