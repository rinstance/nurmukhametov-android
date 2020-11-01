package com.example.myapplication.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.fragments.ImagesFragment
import com.example.myapplication.fragments.InitFragment
import com.example.myapplication.fragments.PostFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null)
            loadFragment(InitFragment())

        bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.page_init -> loadFragment(InitFragment())
                R.id.page_list -> loadFragment(PostFragment())
                R.id.page_images -> loadFragment(ImagesFragment())
            }
            true
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    companion object {
        fun log(s: String) {
            Log.d("WTF", s)
        }
    }
}
