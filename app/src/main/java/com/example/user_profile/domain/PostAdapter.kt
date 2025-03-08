package com.example.user_profile.domain

import android.app.AlertDialog
import android.widget.Button
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.user_profile.R
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil

/**
 * Адаптер для поста.
 */
class PostAdapter: RecyclerView.Adapter<PostAdapter.PostViewHolder>(){

    private var posts: List<Post> = emptyList()

    fun submitList(newPosts: List<Post>) {
        val diffResult = DiffUtil.calculateDiff(PostDiffCallback(posts, newPosts))
        posts = newPosts
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.post, parent, false)
        return PostViewHolder(view)
    }

    override fun getItemCount() = posts.size

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(posts[position])
    }

    inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val postText: TextView = itemView.findViewById(R.id.postDescription)
        private val postImage: ImageView = itemView.findViewById(R.id.postImage)
        private val likesCount: TextView = itemView.findViewById(R.id.likesCount)
        private val commentsCount: TextView = itemView.findViewById(R.id.commentsCount)
        private val likeButton: ImageButton = itemView.findViewById(R.id.likeButton)
        private val commentButton: Button = itemView.findViewById(R.id.commentButton)

        fun bind(post: Post) {
            postText.text = post.description
            likesCount.text = post.likesCount.toString()
            commentsCount.text = itemView.context.getString(R.string.comments_count, post.commentsCount)

            if (post.imageUrl != null) {
                Glide.with(itemView.context).load(post.imageUrl).into(postImage)
                postImage.visibility = View.VISIBLE
            } else {
                postImage.visibility = View.GONE
            }

            likeButton.setOnClickListener {
                onLikeClick(post)
            }

            commentButton.setOnClickListener {
                onCommentClick(post)
            }
        }

        private fun showCommentWindow() {
            val dialogView = LayoutInflater.from(itemView.context).inflate(R.layout.message_window, null)

            val dialog = AlertDialog.Builder(itemView.context)
                .setView(dialogView)
                .setTitle(R.string.comment_window_title)
                .create()

            val sendButton: Button = dialogView.findViewById(R.id.sendButton)
            val messageInput: EditText = dialogView.findViewById(R.id.messageInput)

            sendButton.setOnClickListener {
                val message = messageInput.text.toString()
                if (message.isNotEmpty()) {
                    Toast.makeText(itemView.context, "Сообщение успешно отправлено: $message", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(itemView.context, "Введите сообщение", Toast.LENGTH_SHORT).show()
                }
                dialog.dismiss()
            }

            dialog.show()
        }

        private fun onLikeClick(post: Post) {
            post.isLiked = !post.isLiked
            val newLikeIcon = if (post.isLiked) {
                post.likesCount++
                R.drawable.like_active
            } else {
                post.likesCount--
                R.drawable.like_inactive
            }

            likesCount.text = post.likesCount.toString()
            likeButton.setImageResource(newLikeIcon)
        }

        private fun onCommentClick(post: Post) {
            post.commentsCount++
            commentsCount.text = itemView.context.getString(R.string.comments_count, post.commentsCount)
            showCommentWindow()
        }
    }

    class PostDiffCallback(
        private val oldList: List<Post>,
        private val newList: List<Post>
    ) : DiffUtil.Callback() {

        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }
    }
}