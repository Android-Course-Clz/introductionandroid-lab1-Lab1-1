import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.itmo.rurevange.lab1.PostDiffCallback
import dev.itmo.rurevange.lab1.R
import dev.itmo.rurevange.lab1.models.Post

class PostAdapter(
    private val onLikeClick: (Post) -> Unit,
    private val onCommentClick: (Post) -> Unit
) : ListAdapter<Post, PostAdapter.PostViewHolder>(PostDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_post, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)

        holder.postText.text = post.text

        if (post.imageUrl != null) {
            holder.postImage.visibility = View.VISIBLE

            Glide.with(holder.postImage.context)
                .load(post.imageUrl)
                .centerCrop()
                .into(holder.postImage)
        } else {
            holder.postImage.visibility = View.GONE
        }

        holder.likesText.text = "${post.likesCount} лайков"
        holder.commentsText.text = "${post.commentsCount} комментариев"

        if (post.isLiked) {
            holder.likeButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_like_filled, 0, 0, 0)
            holder.likeButton.setTextColor(holder.likeButton.context.getColor(R.color.colorAccent))
        } else {
            holder.likeButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_like, 0, 0, 0)
            holder.likeButton.setTextColor(holder.likeButton.context.getColor(R.color.textSecondary))
        }

        holder.likeButton.setOnClickListener { onLikeClick(post) }
        holder.commentButton.setOnClickListener { onCommentClick(post) }
    }

    class PostViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val postText: TextView = view.findViewById(R.id.postTextView)
        val postImage: ImageView = view.findViewById(R.id.postImageView)
        val likesText: TextView = view.findViewById(R.id.likesTextView)
        val commentsText: TextView = view.findViewById(R.id.commentsTextView)
        val likeButton: Button = view.findViewById(R.id.likeButton)
        val commentButton: Button = view.findViewById(R.id.commentButton)
    }
}