package dev.itmo.rurevange.lab1.models

data class Post(
    val id: String,
    val text: String,
    val imageUrl: String? = null,
    val likesCount: Int,
    val commentsCount: Int,
    val isLiked: Boolean = false
)