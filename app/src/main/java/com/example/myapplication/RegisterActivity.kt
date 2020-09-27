package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
    }

    fun click(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("name", et_name.text.toString())
        intent.putExtra("mail", et_mail.text.toString())
        intent.putExtra("id", et_id.text.toString())
        startActivity(intent)
    }
}
