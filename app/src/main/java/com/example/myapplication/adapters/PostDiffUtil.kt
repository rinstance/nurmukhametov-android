package com.example.myapplication.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.myapplication.activities.MainActivity
import com.example.myapplication.models.Post

class PostDiffUtil : DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean = (oldItem.id == newItem.id)

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean = (oldItem == newItem)
}