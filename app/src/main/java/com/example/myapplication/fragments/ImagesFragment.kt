package com.example.myapplication.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.myapplication.R
import com.example.myapplication.adapters.images_adapter.ImageAdapter
import com.example.myapplication.models.PostData
import kotlinx.android.synthetic.main.fragment_images.*
import kotlin.coroutines.coroutineContext

class ImagesFragment : Fragment() {
    private var list = PostData.list
    private lateinit var imageAdapter: ImageAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        imageAdapter = ImageAdapter(context)
        images_recycler.apply {
            adapter = imageAdapter
            addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
        }
        imageAdapter.submitList(list)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_images, container, false)
    }
}