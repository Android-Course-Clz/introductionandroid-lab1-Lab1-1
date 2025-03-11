package com.e.profile

import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val postText: TextView = itemView.findViewById(R.id.postText)
    private val postImage: ImageView = itemView.findViewById(R.id.postImage)
    private val likesCount: TextView = itemView.findViewById(R.id.likesCount)
    private val commentsCount: TextView = itemView.findViewById(R.id.commentsCount)
    private val likeButton: ImageButton = itemView.findViewById(R.id.like)
    private val commentButton: ImageButton = itemView.findViewById(R.id.comment)

    fun bind(post: Post) {
        postText.text = post.text

        if (post.imageUrl != null && post.imageUrl!!.isNotEmpty()) {
            Glide.with(itemView.context)
                .load(post.imageUrl)
                .into(postImage)
            postImage.visibility = View.VISIBLE
        } else {
            postImage.visibility = View.GONE
        }

        likeButton.setImageResource(R.drawable.like)
        var isLiked = false
        likeButton.setOnClickListener {
            if (isLiked) {
                likeButton.setImageResource(R.drawable.like)
                post.likes--
                likesCount.text = String.format(post.likes.toString())
            } else {
                likeButton.setImageResource(R.drawable.like_pressed)
                post.likes++
                likesCount.text = String.format(post.likes.toString())
            }
            isLiked = !isLiked
        }

        likesCount.text = String.format(post.likes.toString())
        commentsCount.text = String.format(post.comments.toString())

        commentButton.setOnClickListener { }
    }
}