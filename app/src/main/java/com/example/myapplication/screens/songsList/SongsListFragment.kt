package com.example.myapplication.screens.songsList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.ISongAidlInterface
import com.example.myapplication.MainActivity
import com.example.myapplication.Song
import com.example.myapplication.adapters.SongAdapter
import com.example.myapplication.repository.SongRepository
import com.example.myapplication.screens.song.SongFragment
import com.example.myapplication.R
import kotlinx.android.synthetic.main.fragment_songs_list.*

class SongsListFragment : Fragment() {

    private var adapter: SongAdapter? = null
    var service: ISongAidlInterface? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_songs_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activity = (activity as MainActivity?)
        service = activity?.songService
        adapter = SongAdapter(SongRepository.songsList as ArrayList<Song>) { item, position ->
            val transaction = activity?.supportFragmentManager?.beginTransaction()

            transaction?.addToBackStack(null)
            item.setOnClickListener {
                if (service?.isMPReleased == true
                    || service?.song != SongRepository.songsList[position]
                ) {
                    service?.setCurrentSong(position)
                }

                transaction?.replace(
                    R.id.frameLayout,
                    SongFragment()
                )?.commit()

            }
        }
        rv_song.adapter = adapter
    }
}