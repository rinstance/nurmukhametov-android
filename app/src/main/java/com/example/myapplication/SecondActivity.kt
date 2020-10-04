package com.example.myapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
    }

    fun sendData(view: View) {
        setResult(Activity.RESULT_OK, Intent().apply {
            putExtra("text","Данные из неявного интента получены")
        })
        finish()
    }
}
