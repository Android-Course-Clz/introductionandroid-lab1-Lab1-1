package ru.itmo.labwork1.models

data class Post(
    val text: String,
    val imageURL: String?,
    var likes: Int,
    var comments: Int
)
