package com.example.myapplication.auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.navigation.NavController
import com.example.myapplication.R
import com.example.myapplication.main.MainActivity
import ru.terrakok.cicerone.android.support.SupportAppScreen

object AuthScreens {
    object LoginFragment : SupportAppScreen() {
        fun navigate(controller: NavController) {
            controller.navigate(R.id.action_reg_to_login)
        }
    }

    object MainScreen : SupportAppScreen() {
        fun getIntent(context: Context, bundle: Bundle): Intent {
            return Intent(context, MainActivity::class.java).apply {
                putExtras(bundle)
            }
        }
    }
}