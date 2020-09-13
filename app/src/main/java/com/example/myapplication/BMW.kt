package com.example.myapplication

class BMW(override val model: String, override val door_count: Int) : Car(), ICarSound {
    override fun doSound(sound: String) {
        println("Врм-врм")
    }
}