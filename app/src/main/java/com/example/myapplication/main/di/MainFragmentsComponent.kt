package com.example.myapplication.main.di

import com.example.myapplication.main.fragmnets.profile.FirstNestedFragment
import com.example.myapplication.main.fragmnets.profile.ProfileFragment
import com.example.myapplication.main.fragmnets.profile.SecondNestedFragment
import dagger.Subcomponent

@Subcomponent(modules = [MainPresenterModule::class])
interface MainFragmentsComponent {
    fun injectProfile(fragment: ProfileFragment)
    fun injectFirst(fragment: FirstNestedFragment)
    fun injectSecond(fragment: SecondNestedFragment)
}