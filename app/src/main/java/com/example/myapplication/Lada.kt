package com.example.myapplication

class Lada(override val model: String, override val door_count: Int) : Car(), ICarSound {
    override fun doSound(sound: String) {
        println("Брр-брр-брр")
    }
}