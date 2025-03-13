package com.example.myapplication

data class Post(
    var id: Int,
    val text: String,
    val imageSrc: String?,
    var likesCount: Int,
    var isLiked: Boolean,
    var commentsCount: Int,
)
