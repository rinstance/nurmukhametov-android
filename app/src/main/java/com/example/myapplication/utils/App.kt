package com.example.myapplication.utils

import android.app.Application
import androidx.room.Room
import com.example.myapplication.room.AppDatabase


class App : Application() {
    lateinit var database: AppDatabase

    companion object {
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        database = Room.databaseBuilder(this, AppDatabase::class.java, AppDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }
}