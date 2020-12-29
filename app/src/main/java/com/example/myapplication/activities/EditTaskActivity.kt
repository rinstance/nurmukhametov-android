package com.example.myapplication.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.models.Task
import com.example.myapplication.presenters.TaskPresenter
import kotlinx.android.synthetic.main.activity_edit_task.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class EditTaskActivity : AppCompatActivity(), CoroutineScope {
    private val presenter = TaskPresenter()
    private lateinit var task: Task
    override val coroutineContext: CoroutineContext
        get() = SupervisorJob() + Dispatchers.Main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_task)
        val id = intent.getIntExtra("id", -1)
        setView(id)
        button_save.setOnClickListener {
            if (id == -1) {
                val task = Task(edit_title.text.toString(), edit_desc.text.toString())
                launch(coroutineContext) {
                    presenter.save(task)
                    setResult(RESULT_OK)
                    finish()
                }
            } else {
                task.title = edit_title.text.toString()
                task.description = edit_desc.text.toString()
                launch(coroutineContext) {
                    presenter.update(task)
                    setResult(RESULT_OK)
                    finish()
                }
            }
        }
    }

    private fun setView(id: Int) {
        if (id >= 0) {
            launch(coroutineContext) {
                task = presenter.getById(id)
                edit_title.setText(task.title)
                edit_desc.setText(task.description)
            }
        }
    }
}
