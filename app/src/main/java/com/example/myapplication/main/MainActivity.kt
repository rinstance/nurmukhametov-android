package com.example.myapplication.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.myapplication.App
import com.example.myapplication.R
import com.example.myapplication.auth.AuthScreens
import kotlinx.android.synthetic.main.activity_main.*
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Screen
import ru.terrakok.cicerone.android.support.SupportAppScreen
import ru.terrakok.cicerone.commands.BackTo
import ru.terrakok.cicerone.commands.Command
import ru.terrakok.cicerone.commands.Forward
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject lateinit var holder: NavigatorHolder

    private val navController: NavController by lazy {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.mainContainer) as NavHostFragment
        navHostFragment.navController
    }

    private val navigator = object : Navigator {

        override fun applyCommands(commands: Array<out Command>) {
            commands.forEach {
                when (it) {
                    is Forward -> onForward(it.screen)
                    is BackTo -> { }
                    else -> { }
                }
            }
        }

        private fun onForward(screen: Screen) {
            when (screen) {
                is MainScreens.FirstScreen -> screen.navigate(navController)
                is MainScreens.SecondScreen -> screen.navigate(navController)
                is MainScreens.ThirdScreen -> screen.navigate(navController)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setDI()
        setBottomNavigation()
    }

    private fun setDI() {
        App.appComponent
            .mainComponent()
            .inject(this)
    }

    private fun setBottomNavigation() {
        bottom_nav_view?.setOnNavigationItemReselectedListener {  }
        bottom_nav_view?.setupWithNavController(navController)
    }

    override fun onResume() {
        super.onResume()
        holder.setNavigator(navigator)
    }

    override fun onPause() {
        holder.removeNavigator()
        super.onPause()
    }
}