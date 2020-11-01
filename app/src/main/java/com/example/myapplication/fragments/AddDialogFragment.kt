package com.example.myapplication.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.myapplication.R
import com.example.myapplication.activities.MainActivity
import com.example.myapplication.models.Post
import com.example.myapplication.models.PostData
import kotlinx.android.synthetic.main.fragment_add_dialog.*
import kotlinx.android.synthetic.main.fragment_add_dialog.view.*


class AddDialogFragment : DialogFragment() {
    private var list: MutableList<Post> = PostData.list
    private val TITLE = "title"
    private val TEXT = "text"
    private val POSITION = "position"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.setTitle("Add item?")
        val view: View = inflater.inflate(R.layout.fragment_add_dialog, container, false)
        view.button_add.setOnClickListener {
            add()
        }
        view.button_cancel.setOnClickListener {
            close()
        }
        return view
    }

    private fun add() {
        val intent = Intent()
        MainActivity.log(et_title.text.toString())
        intent.apply {
            putExtra(TITLE, et_title.text.toString())
            putExtra(TEXT, et_text.text.toString())
            val position = ed_position.text
            if (position.toString() == "" || Integer.parseInt(position.toString()) >= list.size)
                putExtra(POSITION, -1)
            else
                putExtra(POSITION, Integer.parseInt(position.toString()))
        }
        targetFragment?.onActivityResult(targetRequestCode, Activity.RESULT_OK, intent)
        close()
    }

    private fun close() {
        dismiss()
    }
}