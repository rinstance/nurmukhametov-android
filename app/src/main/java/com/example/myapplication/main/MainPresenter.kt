package com.example.myapplication.main

import ru.terrakok.cicerone.Router

class MainPresenter(
    private val router: Router
) {
    fun openFirst() {
        router.navigateTo(MainScreens.FirstScreen)
    }

    fun openSecond() {
        router.navigateTo(MainScreens.SecondScreen)
    }

    fun openThird() {
        router.navigateTo(MainScreens.ThirdScreen)
    }
}