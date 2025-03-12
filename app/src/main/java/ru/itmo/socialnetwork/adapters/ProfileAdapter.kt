package ru.itmo.socialnetwork.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import ru.itmo.socialnetwork.CommentsBottomSheet
import ru.itmo.socialnetwork.R
import ru.itmo.socialnetwork.extensions.loadAvatar
import ru.itmo.socialnetwork.extensions.loadImage
import ru.itmo.socialnetwork.models.Post
import ru.itmo.socialnetwork.models.User

class ProfileAdapter(
    private val posts: List<Post>,
    private val user: User
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_USER_INFO = 0
        private const val TYPE_POST = 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) TYPE_USER_INFO else TYPE_POST
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if (viewType == TYPE_USER_INFO) {
            val view = inflater.inflate(R.layout.profile_info, parent, false)
            HeaderViewHolder(view)
        } else {
            val view = inflater.inflate(R.layout.item_post, parent, false)
            PostViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HeaderViewHolder -> holder.bind(user)
            is PostViewHolder -> {
                val post = posts[position - 1]
                holder.bind(post, user)
            }
        }
    }

    override fun getItemCount(): Int = posts.size + 1

    class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val username: TextView = view.findViewById(R.id.username)
        private val postsCount: TextView = view.findViewById(R.id.postsCount)
        private val followersCount: TextView = view.findViewById(R.id.followersCount)
        private val followingsCount: TextView = view.findViewById(R.id.followingsCount)
        private val avatar: ImageView = view.findViewById(R.id.avatar)

        fun bind(user: User) {
            username.text = user.username
            postsCount.text = user.postsCount.toString()
            followersCount.text = user.followersCount.toString()
            followingsCount.text = user.followingsCount.toString()

            avatar.loadAvatar(user.avatarUrl)
        }
    }

    class PostViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val avatar: ImageView = view.findViewById(R.id.postAvatar)
        private val username: TextView = view.findViewById(R.id.postUsername)
        private val image: ImageView = view.findViewById(R.id.postImage)
        private val description: TextView = view.findViewById(R.id.postDescription)
        private val likeButton: ImageView = view.findViewById(R.id.likeButton)
        private val likeCount: TextView = view.findViewById(R.id.likeCount)
        private val commentCount: TextView = view.findViewById(R.id.commentCount)
        private val commentButton: ImageView = view.findViewById(R.id.commentButton)
        private val descriptionUsername: TextView = view.findViewById(R.id.postDescriptionUsername)
        private val postTime: TextView = view.findViewById(R.id.postTime)

        fun bind(post: Post, user: User) {
            username.text = user.username
            description.text = post.description
            descriptionUsername.text = user.username
            postTime.text = post.time

            likeCount.text = post.likes.toString()
            commentCount.text = post.comments.size.toString()

            likeButton.setImageResource(
                if (post.isLiked) R.drawable.ic_heart_filled else R.drawable.ic_heart
            )

            avatar.loadAvatar(user.avatarUrl)
            image.loadImage(post.imageUrl)

            likeButton.setOnClickListener {
                if (post.isLiked) {
                    post.likes--
                } else {
                    post.likes++
                }
                post.isLiked = !post.isLiked

                likeCount.text = post.likes.toString()
                likeButton.setImageResource(
                    if (post.isLiked) R.drawable.ic_heart_filled else R.drawable.ic_heart
                )
            }

            commentButton.setOnClickListener {
                val context = itemView.context
                val commentsBottomSheet = CommentsBottomSheet(post, user) {
                    commentCount.text = post.comments.size.toString()
                }
                if (context is AppCompatActivity) {
                    commentsBottomSheet.show(
                        context.supportFragmentManager,
                        "CommentsBottomSheet"
                    )
                }
            }
        }
    }
}
