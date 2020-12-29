package com.example.myapplication.presenters

import com.example.myapplication.activities.TasksActivity
import com.example.myapplication.models.Task
import com.example.myapplication.repositories.TaskRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class TaskPresenter : CoroutineScope {
    private val taskRepository = TaskRepository()
    override val coroutineContext: CoroutineContext
        get() = SupervisorJob() + Dispatchers.IO

    suspend fun getTaskList(): List<Task> =
        withContext(coroutineContext) { taskRepository.getTasks() }

    suspend fun getById(id: Int): Task =
        withContext(coroutineContext) { taskRepository.getById(id) }

    suspend fun save(task: Task) =
        withContext(coroutineContext) { taskRepository.save(task) }

    suspend fun update(task: Task) =
        withContext(coroutineContext) { taskRepository.update(task) }

    suspend fun delete(task: Task) =
        withContext(coroutineContext) { taskRepository.delete(task) }

    suspend fun deleteAll() =
        withContext(coroutineContext) { taskRepository.deleteAll() }
}