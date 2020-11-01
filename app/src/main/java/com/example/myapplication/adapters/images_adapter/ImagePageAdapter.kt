package com.example.myapplication.adapters.images_adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager.widget.PagerAdapter
import com.example.myapplication.R


class ImagePageAdapter(
    private val images: List<Int>,
    private val context: Context
) : PagerAdapter() {
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(
            R.layout.images_pager_item,
            container,
            false
        )
        val image = view.findViewById<ImageView>(R.id.image)
        image.setImageResource(images[position])
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as ConstraintLayout)
    }

    override fun getCount(): Int = images.size

    override fun isViewFromObject(view: View, `object`: Any): Boolean = (view == `object`)
}