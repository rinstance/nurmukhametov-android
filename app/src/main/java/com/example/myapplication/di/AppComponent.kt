package com.example.myapplication.di

import com.example.myapplication.auth.di.AuthComponent
import com.example.myapplication.main.di.MainComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [CiceroneModule::class])
interface AppComponent {
    fun authComponent() : AuthComponent
    fun mainComponent() : MainComponent
}