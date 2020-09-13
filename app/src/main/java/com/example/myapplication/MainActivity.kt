package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bmw = BMW("X5", 2)
        val lada = Lada("Granta", 4)
        if (lada.isSportCar)
            println("Lada is sport car")
        else
            println("BMW is awesome")
    }
}
