package com.example.myapplication.main

import androidx.navigation.NavController
import com.example.myapplication.R
import ru.terrakok.cicerone.android.support.SupportAppScreen

object MainScreens {
    object FirstScreen: SupportAppScreen() {
        fun navigate(controller: NavController) {
            controller.navigate(R.id.action_profile_to_firstNested)
        }
    }

    object SecondScreen: SupportAppScreen() {
        fun navigate(controller: NavController) {
            controller.navigate(R.id.action_firstNested_to_secondNested)
        }
    }

    object ThirdScreen: SupportAppScreen() {
        fun navigate(controller: NavController) {
            controller.navigate(R.id.action_secondNested_to_thirdNested)
        }
    }
}