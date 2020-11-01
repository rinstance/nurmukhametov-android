package com.example.myapplication.models

import com.example.myapplication.R

object PostData {
    val objects = arrayOf(
        Post(
            1, "First", "First text",
            arrayListOf(R.drawable.monkey, R.drawable.dog)
        ),
        Post(
            2, "Second", "Second text",
            arrayListOf(R.drawable.straus)
        ),
        Post(
            3, "Third", "Third text",
            arrayListOf(R.drawable.tiger, R.drawable.straus, R.drawable.red)
        )
    )
    val list: MutableList<Post> = objects.toMutableList()
}
