package com.example.lab_1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lab_1.models.Post
import com.example.lab_1.repositories.CommentsRepository

class PostAdapter(
    var posts: List<Post>,
    private val onLikeClick: (Post) -> Unit,
    private val commentsRepository: CommentsRepository,
) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {
    class PostViewHolder(postView: View) : RecyclerView.ViewHolder(postView) {
        private val imageView: ImageView = postView.findViewById(R.id.ivPostImage)
        private val descriptionTextView: TextView = postView.findViewById(R.id.tvPostDescription)
        private val likesTextView: TextView = postView.findViewById(R.id.tvPostLikes)
        private val likeButton: ImageButton = postView.findViewById(R.id.ibLikePost)
        private val commentButton: ImageButton = postView.findViewById(R.id.ibCommentPost)
        private val etCommentInput: EditText = itemView.findViewById(R.id.etCommentInput)

        fun bind(post: Post, onLikeClick: (Post) -> Unit, commentsRepository: CommentsRepository) {
            Glide.with(itemView).load(post.image).into(imageView)
            descriptionTextView.text = post.description
            likesTextView.text = post.likes.toString()

            likeButton.setOnClickListener { onLikeClick(post) }
            commentButton.setOnClickListener {
                val commentText = etCommentInput.text.toString().trim()
                if (commentText.isNotEmpty()) {
                    commentsRepository.addComment(post.id, commentText)
                    post.comments++
                    etCommentInput.text.clear()
                }
            }
        }

        fun updateDescription(description: String) {
            descriptionTextView.text = description
        }

        fun updateImage(image: String) {
            Glide.with(itemView).load(image).into(imageView)
        }

        fun updateLikes(likes: Int) {
            likesTextView.text = likes.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_post, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(posts[position], onLikeClick, commentsRepository)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int, payloads: List<Any>) {
        val post = posts[position]
        if (payloads.isNotEmpty()) {
            val payload = payloads[0] as List<*>
            for (item in payload) {
                when (item) {
                    "description" -> holder.updateDescription(post.description)
                    "image" -> holder.updateImage(post.image)
                    "likes" -> holder.updateLikes(post.likes)
                }
            }
        } else {
            onBindViewHolder(holder, position)
        }
    }

    override fun getItemCount() = posts.size

    fun updateList(newList: List<Post>) {
        val diffCallback = PostDiffCallback(posts, newList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        this.posts = newList
        diffResult.dispatchUpdatesTo(this)
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

        override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
            val oldItem = oldList[oldItemPosition]
            val newItem = newList[newItemPosition]
            val payloads = mutableListOf<String>()

            if (oldItem.description != newItem.description) payloads.add("description")
            if (oldItem.image != newItem.image) payloads.add("image")
            if (oldItem.likes != newItem.likes) payloads.add("likes")

            return if (payloads.isEmpty()) super.getChangePayload(
                oldItemPosition,
                newItemPosition
            ) else return payloads
        }
    }
}
