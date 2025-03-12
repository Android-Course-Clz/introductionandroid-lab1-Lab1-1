package com.chtonad0000.profileapp

data class Post(
    val id: Long,
    val text: String,
    val imageUrl: String?,
    var likes: Int,
    var comments: Int
)