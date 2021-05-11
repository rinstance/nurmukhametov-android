package com.example.myapplication.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.myapplication.App
import com.example.myapplication.R
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Screen
import ru.terrakok.cicerone.commands.BackTo
import ru.terrakok.cicerone.commands.Command
import ru.terrakok.cicerone.commands.Forward
import javax.inject.Inject

class AuthActivity : AppCompatActivity() {
    @Inject lateinit var holder: NavigatorHolder
    private lateinit var dataAction: DataAction

    interface DataAction {
        fun getData(): Bundle
    }

    fun setDataAction(dataAction: DataAction) {
        this.dataAction = dataAction
    }

    private val navController: NavController by lazy {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.authContainer) as NavHostFragment
        navHostFragment.navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        setDI()
    }

    private val navigator = object : Navigator {

        override fun applyCommands(commands: Array<out Command>) {
            commands.forEach {
                when (it) {
                    is Forward -> onForward(it.screen)
                }
            }
        }

        private fun onForward(screen: Screen) {
            when (screen) {
                is AuthScreens.LoginFragment -> screen.navigate(navController)
                is AuthScreens.MainScreen -> {
                    screen.getIntent(this@AuthActivity, dataAction.getData()).let {
                        startActivity(it)
                        finish()
                    }
                }
            }
        }
    }

    private fun setDI() {
        App.appComponent
            .authComponent()
            .inject(this)
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
