package ru.itmo.socialnetwork.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.itmo.socialnetwork.R
import ru.itmo.socialnetwork.extensions.loadAvatar
import ru.itmo.socialnetwork.models.Comment

class CommentAdapter(private val comments: List<Comment>) : RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_comment, parent, false)
        return CommentViewHolder(view)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bind(comments[position])
    }

    override fun getItemCount(): Int = comments.size

    class CommentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val avatar: ImageView = view.findViewById(R.id.commentAvatar)
        private val username: TextView = view.findViewById(R.id.commentUsername)
        private val text: TextView = view.findViewById(R.id.commentText)

        fun bind(comment: Comment) {
            username.text = comment.user.username
            text.text = comment.text

            avatar.loadAvatar(comment.user.avatarUrl)
        }
    }
}
