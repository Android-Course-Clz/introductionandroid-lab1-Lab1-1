package com.example.app

import android.R
import android.R.attr.button
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.app.databinding.MainActivityBinding
import com.example.app.databinding.PostBinding


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

class PostsAdapter : RecyclerView.Adapter<PostsAdapter.PostsViewHolder>() {

    private var onClickListener: OnClickListener? = null

    var data: List<Post> = emptyList()
        set(newValue) {
            val postDiffUtil = PostDiffUtil(field, newValue)
            val postDiffUtilResult = DiffUtil.calculateDiff(postDiffUtil)
            field = newValue
            postDiffUtilResult.dispatchUpdatesTo(this@PostsAdapter)
        }

    inner class PostsViewHolder(val binding: PostBinding) : RecyclerView.ViewHolder(binding.root) {
        private lateinit var likeButton: Button
        private lateinit var commentsButton: Button

        init {
            likeButton = binding.likesCount
            commentsButton = binding.commentsCount

            // Setup click listener for button
            likeButton.setOnClickListener(View.OnClickListener {
                    var likes = likeButton.text.toString().toInt()
                    likes += 1
                    likeButton.text = "${likes}"
            })

            commentsButton.setOnClickListener(View.OnClickListener {
                var comments = commentsButton.text.toString().toInt()
                comments += 1
                commentsButton.text = "${comments}"
            })
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PostBinding.inflate(inflater, parent, false)
        return PostsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        val post = data[position]
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
}
