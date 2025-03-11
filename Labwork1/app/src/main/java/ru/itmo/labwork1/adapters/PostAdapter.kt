package ru.itmo.labwork1.adapters

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.itmo.labwork1.R
import ru.itmo.labwork1.databinding.ItemPostBinding
import ru.itmo.labwork1.models.Post

class PostsAdapter : RecyclerView.Adapter<PostsAdapter.PostViewHolder>() {

    private val posts = mutableListOf<Post>()

    fun submitList(newPosts: List<Post>) {
        posts.clear()
        posts.addAll(newPosts)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(posts[position])
    }

    override fun getItemCount() = posts.size

    class PostViewHolder(private val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(post: Post) {
            binding.postTextView.text = post.text
            if (post.text == "") binding.postTextView.visibility = View.GONE
            binding.likesTextView.text = post.likes.toString()
            binding.commentsTextView.text = post.comments.toString()

            post.imageURL?.let { url ->
                binding.postImageView.visibility = View.VISIBLE
                Glide.with(binding.root)
                    .load(url)
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .into(binding.postImageView)
            } ?: run {
                binding.postImageView.visibility = View.GONE
            }

            var isLiked = false
            binding.likesIcon.setOnClickListener {
                if (isLiked) {
                    binding.likesIcon.setImageResource(R.drawable.unlike)
                    post.likes--
                } else {
                    binding.likesIcon.setImageResource(R.drawable.like)
                    post.likes++
                }
                isLiked = !isLiked
                binding.likesTextView.text = post.likes.toString()
            }

            binding.commentsIcon.setOnClickListener {
                showCommentDialog(post)
            }
        }

        private fun showCommentDialog(post: Post) {
            val context = binding.root.context

            val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_comment, null)
            val editTextComment = dialogView.findViewById<EditText>(R.id.editTextComment)

            AlertDialog.Builder(context)
                .setTitle("Добавить комментарий")
                .setView(dialogView)
                .setPositiveButton("Отправить") { _, _ ->
                    val commentText = editTextComment.text.toString()
                    if (commentText.isNotEmpty()) {
                        post.comments++
                        binding.commentsTextView.text = post.comments.toString()
                    }
                }
                .setNegativeButton("Отмена", null)
                .show()
        }
    }
}
