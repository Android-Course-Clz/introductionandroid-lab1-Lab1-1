package com.example.lab_1.models

data class Post(
    val id: Int,
    var image: String,
    var description: String,
    var likes: Int = 0,
    var comments: Int = 0
)