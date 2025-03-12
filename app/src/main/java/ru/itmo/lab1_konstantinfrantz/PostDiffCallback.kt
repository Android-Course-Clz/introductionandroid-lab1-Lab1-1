package ru.itmo.lab1_konstantinfrantz

import androidx.recyclerview.widget.DiffUtil

class PostDiffCallback(
    private val oldPosts: List<Post>,
    private val newPosts: List<Post>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldPosts.size

    override fun getNewListSize(): Int = newPosts.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldPosts[oldItemPosition].id == newPosts[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldPosts[oldItemPosition] == newPosts[newItemPosition]
    }
}