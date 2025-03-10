package ru.SAHEKg.lab1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class PostAdapter(private val posts: List<Post>) :
    RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    class PostViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val avatar: ImageView = view.findViewById(R.id.avatar)
        val username: TextView = view.findViewById(R.id.name)
        val image: ImageView = view.findViewById(R.id.contentImage)
        val text: TextView = view.findViewById(R.id.contentText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_post, parent, false)
        return PostViewHolder(view)
    }

    override fun getItemCount(): Int {
        return posts.size;
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = posts[position]

        holder.username.text = post.username
        holder.text.text = post.text

        Glide.with(holder.itemView.context)
            .load(post.avatar)
            .placeholder(R.drawable.ic_android_black_24dp)
            .circleCrop()
            .into(holder.avatar)

        Glide.with(holder.itemView.context)
            .load(post.image)
            .placeholder(R.drawable.ic_android_black_24dp)
            .into(holder.image)
        }
}