package com.zcchr.intro

data class Post(
    val id: Int,
    val name: String,
    val username: String,
    var likesCount: Int,
    var commentsCount: Int,
    val textContent: String? = null,
    val imageUrl: String? = null
)
