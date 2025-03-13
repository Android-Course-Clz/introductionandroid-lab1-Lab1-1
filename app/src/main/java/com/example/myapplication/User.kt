package com.example.myapplication

data class User(
    var id: Int,
    val fullName: String,
    val nickname: String,
    val imageSrc: String?,
    var followersCount: Int,
    var followingsCount: Int,
    var postsCount: Int,
    var isFollowed: Boolean,
)