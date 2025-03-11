package ru.SAHEKg.lab1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class PostAdapter(private val posts: List<Post>) :
    RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    class PostViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val avatar: ImageView = view.findViewById(R.id.avatar)
        val username: TextView = view.findViewById(R.id.name)
        val image: ImageView = view.findViewById(R.id.contentImage)
        val text: TextView = view.findViewById(R.id.contentText)
        val buttonLike = view.findViewById<ImageButton>(R.id.buttonLike)
        val buttonComment = view.findViewById<ImageButton>(R.id.buttonComment)
        val buttonShare = view.findViewById<ImageButton>(R.id.buttonShare)
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

        holder.buttonLike.setOnClickListener {
            post.isLiked = !post.isLiked
            Toast.makeText(holder.itemView.context, "Лайк на пост ${post.id}", Toast.LENGTH_SHORT).show()
            holder.buttonLike.setImageResource(if (post.isLiked) R.drawable.ic_like_filled else R.drawable.ic_like)
        }

        holder.buttonComment.setOnClickListener {
            Toast.makeText(holder.itemView.context, "Коммент на пост ${post.id}", Toast.LENGTH_SHORT) .show()
        }

        holder.buttonShare.setOnClickListener {
            Toast.makeText(holder.itemView.context, "Поделиться постом ${post.id}", Toast.LENGTH_SHORT) .show()
        }

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
