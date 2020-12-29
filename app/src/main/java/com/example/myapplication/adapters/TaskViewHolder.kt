package com.example.myapplication.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.models.Task
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.task_item.*

class TaskViewHolder(
    override val containerView: View,
    private val click: (Task) -> Unit,
    private val delete: (Task) -> Unit
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(task: Task) {
        text_title.text = task.title
        itemView.setOnClickListener { click(task) }
        image_delete.setOnClickListener { delete(task) }
    }
}