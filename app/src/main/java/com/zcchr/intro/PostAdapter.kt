package com.zcchr.intro

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class PostAdapter : ListAdapter<Post, PostAdapter.PostViewHolder>(PostDiffCallback()) {
    class PostViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val textName: TextView = view.findViewById(R.id.textPostName)
        private val textUsername: TextView = view.findViewById(R.id.textPostUsername)
        private val textContent: TextView = view.findViewById(R.id.textContent)
        private val imagePost: ImageView = view.findViewById(R.id.imagePost)
        private val buttonLike: ImageView = view.findViewById(R.id.btnLike)
        private val textLikesCount: TextView = view.findViewById(R.id.textLikesCount)
        private val buttonComment: ImageView = view.findViewById(R.id.btnComment)
        private val textCommentsCount: TextView = view.findViewById(R.id.textCommentsCount)

        fun bind(post: Post) {
            textName.text = post.name
            textUsername.text = post.username

            textContent.isVisible = !post.textContent.isNullOrBlank()
            imagePost.isVisible = !post.imageUrl.isNullOrBlank()

            textContent.text = post.textContent
            Glide.with(itemView.context)
                .load(post.imageUrl)
                .into(imagePost)

            textLikesCount.text = post.likesCount.toString()
            textCommentsCount.text = post.commentsCount.toString()

            buttonLike.setOnClickListener {
                post.likesCount++
                textLikesCount.text = post.likesCount.toString()
            }

            buttonComment.setOnClickListener {
                post.commentsCount++
                textCommentsCount.text = post.commentsCount.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_post, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class PostDiffCallback : DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem == newItem
        }
    }
}
