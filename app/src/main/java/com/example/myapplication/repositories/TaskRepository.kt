package com.example.myapplication.repositories

import android.content.Context
import com.example.myapplication.models.Task
import com.example.myapplication.room.AppDatabase
import com.example.myapplication.utils.App

class TaskRepository {
    private val appDatabase = App.instance.database
    private val taskDao = appDatabase.getTaskDao()

    fun getTasks(): List<Task> = taskDao.getAll()
    fun getById(id: Int): Task = taskDao.getById(id)
    fun save(task: Task) = taskDao.insert(task)
    fun update(task: Task) = taskDao.update(task)
    fun delete(task: Task) = taskDao.delete(task)
    fun deleteAll() = taskDao.deleteAll()
}