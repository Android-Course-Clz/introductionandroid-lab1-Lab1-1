package ru.itmo.socialnetwork.models

data class User(
    val postsCount: Int,
    val followersCount: Int,
    val followingsCount: Int,
    val username: String,
    val avatarUrl: String
)