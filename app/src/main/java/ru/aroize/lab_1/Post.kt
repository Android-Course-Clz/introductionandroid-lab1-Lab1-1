package ru.aroize.lab_1

data class Item(
    val id: Int,
    val profilePicture: String,
    val postImage: Int,
    val title: String,
    var commentEdit: String,
    var amountOfLikes: Int,
    var likePressed: Boolean,
    var amountOfComments: Int
)