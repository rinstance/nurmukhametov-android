package com.example.myapplication.adapters.post_adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.activities.MainActivity
import com.example.myapplication.models.Post
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.post_item.*

class PostViewHolder(
    override val containerView: View,
    private val deleteClick: (Post) -> Unit
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(post: Post, position: Int) {
        title.text = post.title
        text.text = post.text
        delete_image.setOnClickListener {
            deleteClick(post)
        }
    }
}