package com.example.myapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.myapplication.R
import com.example.myapplication.models.Task

class TaskAdapter(
    private val click: (Task) -> Unit,
    private val delete: (Task) -> Unit
) : ListAdapter<Task, TaskViewHolder>(TaskDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder =
        TaskViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false),
        click, delete)

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}