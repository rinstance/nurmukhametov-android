package com.example.myapplication.adapters.images_adapter

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.activities.MainActivity
import com.example.myapplication.models.Post
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.images_item.*

class ImageViewHolder(
    override val containerView: View,
    private val context: Context?
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(item: Post) {
        if (context != null)
            pager.adapter = ImagePageAdapter(item.imagesList, context)
    }
}