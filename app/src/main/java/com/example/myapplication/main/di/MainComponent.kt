package com.example.myapplication.main.di

import com.example.myapplication.main.MainActivity
import dagger.Subcomponent

@Subcomponent
interface MainComponent {
    fun inject(activity: MainActivity)

    fun mainFragmentsComponent(): MainFragmentsComponent
}