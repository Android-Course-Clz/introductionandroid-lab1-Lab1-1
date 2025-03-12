package com.example.lab_1.models

data class User(
    val id: Int,
    var photo: String,
    var name: String,
    var username: String,
    var subscribersAmount: Int,
    var subscribesAmount: Int,
    var postsAmount: Int
)