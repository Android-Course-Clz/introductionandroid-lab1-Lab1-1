package com.kosandron.socialnetworkprofile.models

data class Profile (
    val name: String,
    val nickname: String,
    var followersCount: Int,
    val followingCount: Int,
    val posts: List<Post>,
    val imageUrl: String
)