package com.kosandron.socialnetworkprofile.models

data class Post(
    var id: Int,
    val description: String,
    var likesCount: Int,
    var isLiked: Boolean,
    var commentsCount: Int,
    val urlImage: String
)