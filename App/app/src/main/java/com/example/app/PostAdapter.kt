package com.example.app

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.app.databinding.MainActivityBinding
import com.example.app.databinding.PostBinding

interface PostActionListener {
    fun onPostLike(post: Post)
    fun onPostComment(post: Post)
}

class PostDiffUtil(
    private val oldList: List<Post>,
    private val newList: List<Post>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size
    override fun getNewListSize(): Int = newList.size
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldPost = oldList[oldItemPosition]
        val newPost = newList[newItemPosition]
        return oldPost.id == newPost.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldPost = oldList[oldItemPosition]
        val newPost = newList[newItemPosition]
        return oldPost == newPost
    }
}

class PostsAdapter(private val postActionListener: PostActionListener) :
    RecyclerView.Adapter<PostsAdapter.PostsViewHolder>(), View.OnClickListener {

    var data: List<Post> = emptyList()
        set(newValue) {
            val postDiffUtil = PostDiffUtil(field, newValue)
            val postDiffUtilResult = DiffUtil.calculateDiff(postDiffUtil)
            field = newValue
            postDiffUtilResult.dispatchUpdatesTo(this@PostsAdapter)
        }

    inner class PostsViewHolder(val binding: PostBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PostBinding.inflate(inflater, parent, false)

        binding.root.setOnClickListener(this)
        binding.commentsCount.setOnClickListener(this)
        binding.likesCount.setOnClickListener(this)
        return PostsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        val post = data[position]

        holder.itemView.tag = post
        holder.binding.likesCount.tag = post
        holder.binding.commentsCount.tag = post

        holder.binding.postText.text = post.text
        holder.binding.likesCount.text = "${post.likes}"
        holder.binding.commentsCount.text = "${post.comments}"

        Glide.with(holder.itemView.context)
            .load(post.photo)
            .into(holder.binding.postImage)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onClick(view: View) {
        val post: Post = view.tag as Post
        when (view.id) {
            R.id.likes_count -> postActionListener.onPostLike(post)
            R.id.comments_count -> postActionListener.onPostComment(post)
        }

    }
}
