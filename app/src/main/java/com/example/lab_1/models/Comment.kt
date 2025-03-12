package com.example.lab_1.models

data class Comment(
    val id: Int,
    val postId: Int,
    var text: String
)