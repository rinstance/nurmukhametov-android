package com.example.myapplication.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.adapters.UserAdapter
import com.example.myapplication.data.UserData
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rv_users.adapter = UserAdapter(UserData.objects) { id ->
            toDetailActivity(id)
        }
    }

    private fun toDetailActivity(id: Int) {
        startActivity(DetailActivity.createIntent(this, id))
    }
}