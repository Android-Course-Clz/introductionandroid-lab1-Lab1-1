package com.example.androidapp

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidapp.databinding.PostItemBinding

class PostAdapter : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {
    private val postList = ArrayList<Post>()

    inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = PostItemBinding.bind(itemView)
        private val commentList = mutableListOf<String>()
        private var commentCount = 0
        private var likeCount = 0

        fun bind(post: Post) {
            clear()

            Glide.with(itemView.context)
                .load(post.imageUrl)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(binding.postButton)

            binding.contentPost.text = post.text

            binding.commentField.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    val comment = binding.commentField.text.toString()
                    if (comment.isNotEmpty()) {
                        commentCount++
                        updateCommentCount()
                        commentList.add(comment)
                        addCommentToView(comment, binding.commentsContainer)
                        binding.commentField.text.clear()
                    }
                    true
                } else {
                    false
                }
            }

            // Обработка лайков
            binding.likeButton.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    likeCount++
                } else {
                    likeCount--
                }
                updateLikeCount()
            }

            binding.commentField.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    binding.displayTextView.text = s.toString()
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            })
        }

        private fun clear() {
            binding.commentsContainer.removeAllViews()
            commentList.clear()
            commentCount = 0
            likeCount = 0
            updateCommentCount()
            updateLikeCount()
        }

        private fun updateCommentCount() {
            binding.commentCountTextView.text = "$commentCount"
        }

        private fun updateLikeCount() {
            binding.likeCountTextView.text = "$likeCount"
        }

        private fun addCommentToView(comment: String, container: LinearLayout) {
            val textView = TextView(itemView.context).apply {
                text = comment
                setPadding(8, 4, 8, 4)
                background = ContextCompat.getDrawable(itemView.context, R.drawable.comment_background)
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    setMargins(0, 4, 0, 4)
                }
            }
            container.addView(textView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.post_item, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(postList[position])
    }

    override fun getItemCount() = postList.size

    fun addPost(post: Post) {
        postList.add(post)
        notifyItemInserted(postList.size - 1)
    }
}
