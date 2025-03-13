package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class PostsViewAdapter(private var author: User, private var posts: List<Post>) :
    RecyclerView.Adapter<PostsViewAdapter.PostViewHolder>() {

    class PostViewHolder(private var author: User, private val view: View) :
        RecyclerView.ViewHolder(view) {
        private val authorAvatar: ImageView = view.findViewById(R.id.author_avatar)
        private val authorName: TextView = view.findViewById(R.id.author_name)
        private val authorNickname: TextView = view.findViewById(R.id.author_nickname)
        private val postText: TextView = view.findViewById(R.id.post_text)
        private val postImage: ImageView = view.findViewById(R.id.post_image)
        private val postLikes: TextView = view.findViewById(R.id.likes_count)
        private val likesButton: ImageButton = view.findViewById(R.id.like_button)
        private val postComments: TextView = view.findViewById(R.id.comments_count)
        private val commentsButton: ImageButton = view.findViewById(R.id.comments_button)

        fun bind(post: Post) {
            authorName.text = author.fullName
            authorNickname.text = "@" + author.nickname
            if (author.imageSrc != null) {
                Glide.with(authorAvatar.context).load(author.imageSrc).into(authorAvatar)
            }

            postText.text = post.text
            if (post.imageSrc != null) {
                postImage.visibility = View.VISIBLE
                Glide.with(postImage.context).load(post.imageSrc).into(postImage)
            } else {
                postImage.visibility = View.GONE
            }

            postLikes.text = post.likesCount.toString()
            if (post.isLiked) {
                likesButton.setBackgroundResource(R.drawable.like_filled)
            }

            likesButton.setOnClickListener {
                if (!post.isLiked) {
                    likesButton.setBackgroundResource(R.drawable.like_filled)
                    post.likesCount++
                    post.isLiked = true
                    postLikes.text = post.likesCount.toString()
                } else {
                    likesButton.setBackgroundResource(R.drawable.like)
                    post.likesCount--
                    post.isLiked = false
                    postLikes.text = post.likesCount.toString()
                }
            }

            postComments.text = post.commentsCount.toString()
            commentsButton.setOnClickListener {
                post.commentsCount++
                postComments.text = post.commentsCount.toString()
                Toast.makeText(view.context, "New comment sent", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_post, parent, false)
        return PostViewHolder(author, view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(posts[position])
    }

    override fun getItemCount(): Int = posts.size
}