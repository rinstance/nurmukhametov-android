package com.example.myapplication.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
data class Task(
    var title: String,
    var description: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}