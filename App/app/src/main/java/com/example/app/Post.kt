package com.example.app

data class Post(
    val id: Int,
    val name: String,
    val text: String,
    val likes: Int,
    val photo: String,
    val comments: Int,
    var isLiked: Boolean
)
