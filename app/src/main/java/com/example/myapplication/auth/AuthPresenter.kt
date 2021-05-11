package com.example.myapplication.auth

import ru.terrakok.cicerone.Router

const val test = "test"

class AuthPresenter(
    private val router: Router
) {
    fun openLoginFragment() {
        router.navigateTo(AuthScreens.LoginFragment)
    }

    fun openMainActivity() {
        router.navigateTo(AuthScreens.MainScreen)
    }

    fun openMainActivity(login: String, pass: String): Boolean {
        if (login == test && pass == test) {
            openMainActivity()
            return true
        }
        return false
    }
}