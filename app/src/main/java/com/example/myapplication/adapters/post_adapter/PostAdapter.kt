package com.example.myapplication.adapters.post_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.myapplication.R
import com.example.myapplication.adapters.PostDiffUtil
import com.example.myapplication.models.Post
import kotlin.collections.ArrayList

class PostAdapter(
    private val deleteClick: (Post) -> Unit
) : ListAdapter<Post, PostViewHolder>(PostDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.post_item, parent, false),
            deleteClick
        )
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }

    override fun submitList(list: MutableList<Post>?) =
        super.submitList(if (list != null) ArrayList(list) else list)

}