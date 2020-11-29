package com.example.myapplication

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.myapplication.screens.songsList.SongsListFragment
import com.example.myapplication.service.SongService

class MainActivity : AppCompatActivity() {
    var songService: ISongAidlInterface? = null
    lateinit var builder: NotificationCompat.Builder
    val CHANNEL_ID = "playerChannel"

    private val aidlConnection = object : ServiceConnection {

        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            songService = ISongAidlInterface.Stub.asInterface(service)
            supportFragmentManager.beginTransaction().
            replace(R.id.frameLayout, SongsListFragment()).commit()
        }

        override fun onServiceDisconnected(className: ComponentName) {
            songService = null
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        val intent =
            Intent(this, SongService::class.java)
        bindService(intent, aidlConnection, Context.BIND_AUTO_CREATE)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("LOG_TAG", "MainActivity onServiceDisconnected")
        songService?.let {
            unbindService(aidlConnection)
            songService = null
        }

    }
}
