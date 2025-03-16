package ru.itmo.socialnetwork.models

data class Comment (
    val id: Int,
    val text: String,
    val user: User,
)