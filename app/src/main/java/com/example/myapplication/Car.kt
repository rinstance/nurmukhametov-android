package com.example.myapplication

open class Car(open val model: String = "") {
    open val door_count: Int = 4
    val isSportCar: Boolean
        get() = door_count == 2
}