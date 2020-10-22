package com.example.myapplication.repositories

import com.example.myapplication.R
import com.example.myapplication.data.UserData
import com.example.myapplication.models.User

class UserRepository {
    fun getUserById(id: Int?): User? {
        UserData.objects.forEach {
                u -> if (u.id == id) return u
        }
        return null
    }
}