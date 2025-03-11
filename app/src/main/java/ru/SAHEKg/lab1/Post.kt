package ru.SAHEKg.lab1

data class Post(
    val id: Int,
    val avatar: String,
    val username: String,
    val image: String,
    val text: String,
    var isLiked: Boolean = false
)
