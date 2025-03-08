package com.example.user_profile.domain

/**
 * Модель пользоателя.
 */
data class User(
    var avatarUrl: String,
    val firstName: String,
    val lastName: String,
    val username: String,
    var subscribersCount: Int,
    var subscribedCount: Int,
    var postsCount: Int
)