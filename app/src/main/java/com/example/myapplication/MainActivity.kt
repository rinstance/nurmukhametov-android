package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var isEdit: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener { edit() }
    }

    private fun edit() {
        if (!isEdit) {
            editText.visibility = View.VISIBLE
            textView.visibility = View.INVISIBLE
            editText.setText(textView.text)
            button.text = "Сохранить"
        } else {
            textView.text = editText.text
            editText.visibility = View.INVISIBLE
            textView.visibility = View.VISIBLE
            button.text = "Редактировать"
        }
        isEdit = !isEdit
    }
}
