package com.example.lab_1.repositories

import com.example.lab_1.models.Comment

class CommentsRepository {
    private val comments = mutableListOf<Comment>()
    private var nextId = 1

    fun addComment(postId: Int, text: String) {
        if (text.isNotEmpty()) comments.add(Comment(nextId++, postId, text))
    }
}