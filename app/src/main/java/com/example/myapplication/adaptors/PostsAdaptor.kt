package com.example.myapplication.adaptors

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.databinding.PostBinding
import com.example.myapplication.models.Post

class PostsAdapter : RecyclerView.Adapter<PostsAdapter.PostsViewHolder>() {
    inner class PostsViewHolder(val binding: PostBinding) : RecyclerView.ViewHolder(binding.root) {
    }

    private val differCallback = object : DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem.id == newItem.id && oldItem.text == newItem.text && oldItem.photo_url == newItem.photo_url
                    && oldItem.likes == newItem.likes && oldItem.comments == newItem.comments
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        val binding = PostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        val post = differ.currentList[position]
        holder.binding.postText.text = post.text
        holder.binding.postStats.text = "Likes: ${post.likes}                Comments: ${post.comments}"

        Glide.with(holder.itemView.context)
            .load(post.photo_url)
            .into(holder.binding.postImage)

        holder.binding.likeButton.setOnClickListener {

            if (post.liked) {
                post.likes--;
                holder.binding.likeButton.setImageResource(R.drawable.like_icon)
                post.liked = false;
            } else {
                holder.binding.likeButton.setImageResource(R.drawable.liked_icon)
                post.likes++
                post.liked = true;
            }
            holder.binding.postStats.text = "Likes: ${post.likes}                Comments: ${post.comments}"
        }

        holder.binding.commentButtonn.setOnClickListener {
            post.comments++;
            holder.binding.postStats.text = "Likes: ${post.likes}                Comments: ${post.comments}"
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}