package com.example.user_profile.domain

/**
 * Модель поста.
 */
data class Post(
    val id: Int,
    var description: String,
    val imageUrl: String?,
    var likesCount: Int,
    var commentsCount: Int,
    var isLiked: Boolean = false
)