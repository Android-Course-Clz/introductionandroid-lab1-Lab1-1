package ru.itmo.socialnetwork.models

data class Post(
    val id: Int,
    val imageUrl: String,
    val description: String,
    var likes: Int,
    val time: String,
    var isLiked: Boolean = false,
    var comments: MutableList<Comment>
)


