package com.kosandron.socialnetworkprofile.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.kosandron.socialnetworkprofile.R
import com.kosandron.socialnetworkprofile.databinding.FragmentPostBinding
import com.kosandron.socialnetworkprofile.models.Post
import java.util.Locale

class PostsAdapter : RecyclerView.Adapter<PostsAdapter.PostViewHolder>() {
    inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = FragmentPostBinding.bind(itemView)
        fun bind(post: Post) {
            with(binding) {
                Glide
                    .with(root.context)
                    .load(post.urlImage)
                    .transform(MultiTransformation(FitCenter(), RoundedCorners(12)))
                    .into(postImageView)

                postDescriptionTextView.text = post.description
                likeCountTextView.text = String.format(Locale.US, "%,d", post.likesCount)
                commentCountTextView.text = String.format(Locale.US, "%,d", post.commentsCount)

                likeButton.setOnClickListener {
                    post.isLiked = !post.isLiked
                    if (post.isLiked) {
                        post.likesCount++
                        likeButton.setImageResource(R.drawable.liked)
                    } else {
                        post.likesCount--
                        likeButton.setImageResource(R.drawable.not_liked)
                    }
                    likeCountTextView.text = String.format(Locale.US, "%,d", post.likesCount)
                }

                commentButton.setOnClickListener {
                    if (commentInputLayout.visibility == View.GONE) {
                        commentInputLayout.visibility = View.VISIBLE
                    } else {
                        commentInputLayout.visibility = View.GONE
                    }
                }

                postCommentButton.setOnClickListener {
                    val comment = commentEditText.text.toString().trim()
                    if (comment.isNotEmpty()) {
                        val oldComments = commentsTextView.text.toString()
                        val newCommentText = if (oldComments.isEmpty()) {
                            "- $comment"
                        } else {
                            "$oldComments\n- $comment"
                        }
                        commentsTextView.text = newCommentText
                        commentEditText.text.clear()
                        post.commentsCount++
                        commentCountTextView.text = String.format(Locale.US, "%,d", post.commentsCount)
                        commentInputLayout.visibility = View.GONE
                        commentsTextView.visibility = View.VISIBLE
                    }

                    val inputMethodManager = itemView.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    inputMethodManager.hideSoftInputFromWindow(commentEditText.windowToken, 0)
                }
            }
        }
    }

    private val diffCallback = object : DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem == newItem
        }
    }


    val differ = AsyncListDiffer(this, diffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.fragment_post,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }
}