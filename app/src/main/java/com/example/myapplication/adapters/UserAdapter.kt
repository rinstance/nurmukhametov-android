package com.example.myapplication.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.models.User

class UserAdapter(
    private val list: Array<User>,
    private val click: (Int) -> Unit
) : RecyclerView.Adapter<UserHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder =
        UserHolder.create(parent, click)

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        holder.bind(list[position])
    }

}