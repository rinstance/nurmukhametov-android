package com.example.myapplication.models

data class Post (
    val id: Int,
    val title: String,
    val text: String,
    val imagesList: ArrayList<Int>
)