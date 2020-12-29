package com.example.myapplication.room

import androidx.room.*
import com.example.myapplication.models.Task

@Dao
interface TaskDao {
    @Query("select * from task")
    fun getAll(): List<Task>

    @Query("select * from task where id = :id")
    fun getById(id: Int): Task

    @Query("delete from task")
    fun deleteAll()

    @Insert
    fun insert(task: Task)

    @Update
    fun update(task: Task)

    @Delete
    fun delete(task: Task)
}