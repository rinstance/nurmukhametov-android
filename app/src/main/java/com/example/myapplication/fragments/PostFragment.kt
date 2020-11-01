package com.example.myapplication.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.activities.MainActivity
import com.example.myapplication.adapters.post_adapter.PostAdapter
import com.example.myapplication.models.Post
import com.example.myapplication.models.PostData
import kotlinx.android.synthetic.main.fragment_post.*
import java.util.*

class PostFragment : Fragment() {
    private val REQUEST = 1
    private var list = PostData.list
    private var maxId = list[list.size - 1].id
    private var postAdapter: PostAdapter
    private val TITLE = "title"
    private val TEXT = "text"
    private val POSITION = "position"
    private var images: ArrayList<Int>

    init {
        postAdapter = PostAdapter {
            delete(it)
        }
        images = arrayListOf(
            R.drawable.dog, R.drawable.fon, R.drawable.monkey, R.drawable.red,
            R.drawable.straus, R.drawable.tiger)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST) {
                val post = data?.let {
                    Post(
                        maxId++,
                        it.getStringExtra(TITLE),
                        it.getStringExtra(TEXT),
                        randomImages()
                    )
                }
                val position = data?.getIntExtra(POSITION, -1)
                if (position != null) {
                    if (position < 0)
                        post?.let { list.add(it) }
                    else
                        post?.let { list.add(position, it) }
                }
                MainActivity.log(post?.imagesList.toString())
                postAdapter.submitList(list)
            }
        }
    }

    private fun randomImages(): ArrayList<Int> {
        val imagesList = ArrayList<Int>()
        val r = (0 until images.size).random()
        for (i in 0..r)
            imagesList.add(images[i])
        return imagesList
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recycler_view.apply {
            adapter = postAdapter
            addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
        }
        postAdapter.submitList(list)

        floating_add_button.setOnClickListener {
            val dialogFragment = AddDialogFragment()
            fragmentManager?.let {
                dialogFragment.setTargetFragment(this, REQUEST);
                dialogFragment.show(it, "addDialog")
            }
            postAdapter.submitList(list)
        }
    }

    private fun delete(post: Post) {
        list.removeAt(list.indexOf(post))
        postAdapter.submitList(list)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_post, container, false)
    }
}