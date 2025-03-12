package com.the_emperor_zurg.first_lab.ui.posts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.the_emperor_zurg.first_lab.R
import com.the_emperor_zurg.first_lab.databinding.PostItemBinding

class PostsAdapter(
    private val posts: List<Post>,
    private val showSubscribeButton: Boolean) :
    RecyclerView.Adapter<PostsAdapter.PostViewHolder>() {

    inner class PostViewHolder(private val binding: PostItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private var isLiked = false
        private var likesCount = 0

        fun bind(post: Post) {
            binding.postNickname.text = post.nickname

            Glide.with(binding.root)
                .load(post.imageUrl)
                .centerCrop()
                .into(binding.postImage)

            likesCount = post.likes
            binding.likesCount.text = "Likes: $likesCount"
            binding.likeIcon.setImageDrawable(
                ContextCompat.getDrawable(binding.root.context, R.drawable.ic_heart)
            )

            binding.likeIcon.setOnClickListener {
                isLiked = !isLiked
                if (isLiked) {
                    likesCount++
                    binding.likeIcon.setImageDrawable(
                        ContextCompat.getDrawable(binding.root.context, R.drawable.ic_red_heart)
                    )
                } else {
                    likesCount--
                    binding.likeIcon.setImageDrawable(
                        ContextCompat.getDrawable(binding.root.context, R.drawable.ic_heart)
                    )
                }
                binding.likesCount.text = "Likes: $likesCount"
            }

            binding.commentIcon.setOnClickListener {
                if (binding.commentInputBlock.visibility == View.VISIBLE) {
                    binding.commentInputBlock.visibility = View.GONE
                } else {
                    binding.commentInputBlock.visibility = View.VISIBLE
                }
            }

            binding.commentIcon.setOnLongClickListener {
                binding.commentsBlock.visibility =
                    if (binding.commentsBlock.visibility == View.VISIBLE) View.GONE else View.VISIBLE
                true
            }

            binding.sendButton.setOnClickListener {
                val newComment = binding.commentEditText.text.toString()
                if (newComment.isNotEmpty()) {
                    val commentText = "${post.nickname}: $newComment"
                    post.comments.add(commentText)


                    binding.commentEditText.text.clear()

                    refreshComments(post)

                    binding.commentsBlock.visibility = View.VISIBLE
                }
            }

            refreshComments(post)

            if (showSubscribeButton) {
                binding.subscribeButton.visibility = View.VISIBLE
                updateSubscribeButtonUI(post)

                binding.subscribeButton.setOnClickListener {
                    post.isSubscribed = !post.isSubscribed
                    updateSubscribeButtonUI(post)
                }

            } else {
                binding.subscribeButton.visibility = View.GONE
            }
        }

        private fun refreshComments(post: Post) {
            if (post.comments.isEmpty()) {
                binding.commentsTextView.text = "No comments yet"
            } else {
                binding.commentsTextView.text = post.comments.joinToString(separator = "\n")
            }
        }

        private fun updateSubscribeButtonUI(post: Post) {
            if (post.isSubscribed) {
                binding.subscribeButton.text = "Subscribed"
                binding.subscribeButton.setBackgroundColor(
                    ContextCompat.getColor(binding.root.context, R.color.grey)
                )
                binding.subscribeButton.setTextColor(
                    ContextCompat.getColor(binding.root.context, android.R.color.white)
                )
            } else {
                binding.subscribeButton.text = "Subscribe"
                binding.subscribeButton.setBackgroundColor(
                    ContextCompat.getColor(binding.root.context, R.color.purple_500)
                )
                binding.subscribeButton.setTextColor(
                    ContextCompat.getColor(binding.root.context, R.color.white)
                )
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = PostItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(posts[position])
    }

    override fun getItemCount(): Int = posts.size
}

