package com.the_emperor_zurg.first_lab.ui.posts

data class Post(
    val nickname: String,
    val imageUrl: String,
    var likes: Int,
    var isLiked: Boolean = false,
    var isSubscribed: Boolean = false,
    val comments: MutableList<String> = mutableListOf(),
)
