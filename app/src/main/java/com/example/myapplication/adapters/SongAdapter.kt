package com.example.myapplication.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Song
import com.example.myapplication.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_song.*


class SongAdapter (
    private var list: ArrayList<Song>,
    private val fragmentLambda: (View, Int) -> Unit
) : RecyclerView.Adapter<SongAdapter.SongViewHolder>() {

    inner class SongViewHolder(
        override val containerView: View
    ): RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(song: Song, position: Int) {
            with(song) {
                tv_sm_title.text = name
                tv_sm_author.text = author
                iv_sm_album.setImageResource(song.photoId)
            }

            itemView.setOnClickListener{fragmentLambda(itemView,position)}
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        return SongViewHolder(LayoutInflater.from(parent.context).
        inflate(R.layout.item_song, parent, false))
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        holder.bind(list[position], position)
    }

    override fun getItemCount(): Int = list.size
}