package com.chtonad0000.profileapp

import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class PostAdapter : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    private var posts: List<Post> = emptyList()

    class PostViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val postText: TextView = view.findViewById(R.id.post_text)
        val postImage: ImageView = view.findViewById(R.id.post_image)
        val likeButton: ImageButton = view.findViewById(R.id.btn_like)
        val commentButton: ImageButton = view.findViewById(R.id.btn_comment)
        val likeCount: TextView = view.findViewById(R.id.like_count)
        val commentCount: TextView = view.findViewById(R.id.comment_count)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val drawableLike = holder.likeButton.drawable
        drawableLike?.setColorFilter(PorterDuffColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN))
        val drawableComment = holder.commentButton.drawable
        drawableComment?.setColorFilter(PorterDuffColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN))

        val post = posts[position]
        holder.postText.text = post.text
        holder.likeCount.text = post.likes.toString()
        holder.commentCount.text = post.comments.toString()

        if (post.imageUrl != null) {
            holder.postImage.visibility = View.VISIBLE
            Glide.with(holder.itemView.context).load(post.imageUrl).into(holder.postImage)
        } else {
            holder.postImage.visibility = View.GONE
        }

        holder.likeButton.setOnClickListener {
            post.likes++
            holder.likeCount.text = post.likes.toString()
        }

        holder.commentButton.setOnClickListener {
            post.comments++
            holder.commentCount.text = post.comments.toString()
        }
    }

    override fun getItemCount() = posts.size

    fun submitList(newPosts: List<Post>) {
        val diffCallback = PostDiffCallback(posts, newPosts)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        posts = newPosts
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
    }
}
