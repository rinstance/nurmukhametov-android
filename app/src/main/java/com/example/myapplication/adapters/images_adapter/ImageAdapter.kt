package com.example.myapplication.adapters.images_adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.myapplication.R
import com.example.myapplication.adapters.PostDiffUtil
import com.example.myapplication.models.Post

class ImageAdapter(
    private val context: Context?
) : ListAdapter<Post, ImageViewHolder>(PostDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.images_item, parent, false),
            context)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun submitList(list: MutableList<Post>?) {
        super.submitList(if (list != null) ArrayList(list) else list)
    }
}