package com.example.myapplication.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.repositories.UserRepository
import kotlinx.android.synthetic.main.user_item.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val id = intent.extras?.getInt(ID)
        val user = UserRepository().getUserById(id)
        user?.image_id?.let { image_user.setImageResource(it) }
        tv_name.text = user?.name.toString()
        tv_quote.text = user?.quote.toString()
    }

    companion object {
        private const val ID = "id"
        fun createIntent(activity: Activity, id: Int) =
            Intent(activity, DetailActivity::class.java).apply {
                putExtra(ID, id)
            }
    }
}