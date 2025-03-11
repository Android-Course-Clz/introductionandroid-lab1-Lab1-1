package dev.itmo.rurevange.lab1.models

data class User(
    val id: String,
    val name: String,
    val username: String,
    val avatarUrl: String,
    val postsCount: Int,
    val followersCount: Int,
    val followingCount: Int,
    val isFollowing: Boolean = false
)