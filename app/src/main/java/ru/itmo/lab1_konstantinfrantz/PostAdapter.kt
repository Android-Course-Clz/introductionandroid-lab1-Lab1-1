package ru.itmo.lab1_konstantinfrantz

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class PostAdapter(
    private var posts: List<Post>,
) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(posts[position])
    }

    override fun getItemCount(): Int = posts.size

    fun updatePosts(newPosts: List<Post>) {
        val diffCallback = PostDiffCallback(posts, newPosts)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        posts = newPosts
        diffResult.dispatchUpdatesTo(this)
    }

    inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivPostAvatar: ImageView = itemView.findViewById(R.id.ivPostAvatar)
        private val tvPostAuthorName: TextView = itemView.findViewById(R.id.tvPostAuthorName)
        private val tvPostAuthorNickname: TextView = itemView.findViewById(R.id.tvPostAuthorNickname)
        private val tvPostText: TextView = itemView.findViewById(R.id.tvPostText)
        private val ivPostImage: ImageView = itemView.findViewById(R.id.ivPostImage)
        private val tvLikeCount: TextView = itemView.findViewById(R.id.tvLikeCount)
        private val tvCommentCount: TextView = itemView.findViewById(R.id.tvCommentCount)

        fun bind(post: Post) {
            tvPostAuthorName.text = post.authorName
            tvPostAuthorNickname.text = post.authorNickname
            tvPostText.text = post.text

            Glide.with(itemView.context)
                .load(post.authorAvatarUrl)
                .apply(RequestOptions.circleCropTransform())
                .into(ivPostAvatar)

            if (post.imageUrl != null) {
                Glide.with(itemView.context)
                    .load(post.imageUrl)
                    .into(ivPostImage)
            } else {
                ivPostImage.visibility = View.GONE
            }

            tvLikeCount.text = formatCount(post.likeCount)
            tvCommentCount.text = formatCount(post.commentCount)

            updateLikeState(post)

            tvLikeCount.setOnClickListener {
                val currentPost = posts[getBindingAdapterPosition()]
                currentPost.isLiked = !currentPost.isLiked

                if (currentPost.isLiked) {
                    currentPost.likeCount++
                } else {
                    currentPost.likeCount--
                }

                updateLikeState(currentPost)
                tvLikeCount.text = formatCount(currentPost.likeCount)
            }

            tvCommentCount.setOnClickListener {
                Toast.makeText(itemView.context, "Comment", Toast.LENGTH_SHORT).show()
            }
        }

        private fun updateLikeState(post: Post) {
            val drawableResId = if (post.isLiked) R.drawable.ic_like_filled else R.drawable.ic_like
            val tintColor = if (post.isLiked)
                ContextCompat.getColor(itemView.context, android.R.color.holo_red_light)
            else
                ContextCompat.getColor(itemView.context, android.R.color.darker_gray)

            tvLikeCount.setCompoundDrawablesWithIntrinsicBounds(drawableResId, 0, 0, 0)
            tvLikeCount.compoundDrawables[0]?.setTint(tintColor)
        }

        private fun formatCount(count: Int): String {
            return when {
                count < 1000 -> count.toString()
                count < 1000000 -> String.format("%.1fK", count / 1000.0)
                else -> String.format("%.1fM", count / 1000000.0)
            }
        }
    }
}