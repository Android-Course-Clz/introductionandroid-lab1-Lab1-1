package ru.itmo.lab1_konstantinfrantz

data class Post(
    val id: Long,
    val authorName: String,
    val authorNickname: String,
    val authorAvatarUrl: String,
    val text: String,
    val imageUrl: String?,
    var likeCount: Int,
    var commentCount: Int,
    var isLiked: Boolean
)