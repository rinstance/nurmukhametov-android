package com.example.myapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.models.User
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.user_item.*

class UserHolder(
    override val containerView: View,
    private val click: (Int) -> Unit
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(user: User) {
        image_user.setImageResource(user.image_id)
        tv_name.text = user.name
        tv_quote.text = user.quote
        itemView.setOnClickListener {
            click(user.id)
        }
    }

    companion object {
        fun create(parent: ViewGroup, click: (Int) -> Unit): UserHolder = UserHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false),
            click
        )
    }
}