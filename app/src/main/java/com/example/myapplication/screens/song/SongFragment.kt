package com.example.myapplication.screens.song

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import com.example.myapplication.ISongAidlInterface
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.Song
import kotlinx.android.synthetic.main.fragment_song.*

class SongFragment : Fragment() {
    var service: ISongAidlInterface? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_song, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = (activity as MainActivity?)
        service = activity?.songService
        changeUI(service?.song)
        initialiseSeekBar()


        iv_play.setOnClickListener {
            if (service?.isPlaying == true) {
                iv_play.setImageResource(R.drawable.ic_play)
                service?.pause()
            } else if (service?.isPlaying == false) {
                iv_play.setImageResource(R.drawable.ic_pause)
                service?.play()
            }
        }
        iv_next.setOnClickListener {
            service?.playNext()
            changeUI(service?.song)
        }
        iv_prev.setOnClickListener {
            service?.playPrev()
            changeUI(service?.song)
        }
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                if (p2) {
                    service?.changeTime(p1)
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })
    }

    private fun changeUI(song: Song?) {
        song?.photoId?.let { iv_album.setImageResource(it) }
        tv_author.text = song?.author
        tv_name.text = song?.name

    }

    private fun initialiseSeekBar() {
        seekBar.max = service!!.duration
        var handler: Handler = Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                seekBar.progress = service?.currentPosition!!
                handler.postDelayed(this, 1000)
            }
        }, 0)
    }


}