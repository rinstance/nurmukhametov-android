package com.example.myapplication

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.myapplication.framents.HomeFragment
import com.example.myapplication.framents.ProfileFragment
import com.example.myapplication.framents.SearchFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val fm = supportFragmentManager
    private val homeFragment = HomeFragment()
    private val searchFragment = SearchFragment()
    private val profileFragment = ProfileFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadFragment(homeFragment)
        home.isSelected = true

        val iconsArray: Array<ImageView> =
            arrayOf(home, search, profile)

        home.setOnClickListener {
            bottomImageSelected(home, iconsArray)
            loadFragment(homeFragment)
        }
        search.setOnClickListener {
            bottomImageSelected(search, iconsArray)
            loadFragment(searchFragment)
        }
        profile.setOnClickListener {
            bottomImageSelected(profile, iconsArray)
            loadFragment(profileFragment)
        }
    }

    private fun bottomImageSelected(v: ImageView, array: Array<ImageView>) {
        v.isSelected = true
        for (imageView in array) {
            if (imageView == v)
                continue
            imageView.isSelected = false
        }
    }

    private fun loadFragment(fragment: Fragment) {
        fm.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}
