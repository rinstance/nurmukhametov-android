package com.example.myapplication.repository

import com.example.myapplication.Song
import com.example.myapplication.R

object SongRepository {
    val songsList: List<Song> = arrayListOf(
        Song("SLAVA","Снова я напиваюсь", R.drawable.straus, R.raw.again_drink),
        Song("Dora","Втюрилась", R.drawable.straus, R.raw.dora),
        Song("Нурминчик","Валим валим на гелике", R.drawable.straus, R.raw.valim),
        Song("Нурминчик","Ауф", R.drawable.straus, R.raw.auf),
        Song("Кто-то","Арабская", R.drawable.straus, R.raw.arabskay)
    )
}