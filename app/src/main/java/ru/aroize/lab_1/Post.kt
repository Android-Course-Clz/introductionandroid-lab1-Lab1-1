package ru.aroize.lab_1

data class Item(
    val id: Int,
    val profilePicture: String,
    val postImage: Int,
    val title: String,
    val commentEdit: String,
    val amountOfLikes: Int,
    val likePressed: Boolean,
    val amountOfComments: Int
)