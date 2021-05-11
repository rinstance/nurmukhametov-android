package com.example.myapplication.main.di

import com.example.myapplication.main.MainPresenter
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Router

@Module
class MainPresenterModule {
    @Provides
    fun providePresenter(router: Router) = MainPresenter(router)
}