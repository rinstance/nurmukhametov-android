package com.example.myapplication.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.example.myapplication.R
import com.example.myapplication.adapters.TaskAdapter
import com.example.myapplication.models.Task
import com.example.myapplication.presenters.TaskPresenter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class TasksActivity : AppCompatActivity(), CoroutineScope {
    private lateinit var taskAdapter: TaskAdapter
    private val REQUEST_CODE = 1
    private val presenter = TaskPresenter()
    override val coroutineContext: CoroutineContext
        get() = SupervisorJob() + Dispatchers.Main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setAdapter()
        setToolbar()
        setClickListeners()
    }

    private fun setToolbar() {
        setSupportActionBar(toolbar)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete_all) {
            launch(coroutineContext) {
                presenter.deleteAll()
                updateAdapter()
            }
        }
        return true;
    }

    private fun setClickListeners() {
        fb_add_task.setOnClickListener {
            startDescActivity(-1)
        }
    }

    private fun deleteTask(task: Task) {
        launch(coroutineContext) {
            presenter.delete(task)
            updateAdapter()
        }
    }

    private fun startDescActivity(id: Int) {
        Intent(this, EditTaskActivity::class.java).apply {
            putExtra("id", id)
            startActivityForResult(this, REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK)
            updateAdapter()
    }

    private fun setAdapter() {
        taskAdapter = TaskAdapter(
            click = { it.id?.let { id -> startDescActivity(id) } },
            delete = { deleteTask(it) })
        recycler_tasks.apply {
            adapter = taskAdapter
        }
        updateAdapter()
    }

    private fun updateAdapter() {
        launch(coroutineContext) {
            val taskList = presenter.getTaskList()
            taskAdapter.submitList(taskList)
        }
    }
}
