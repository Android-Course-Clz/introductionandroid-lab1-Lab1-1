package dev.itmo.rurevange.lab1

import PostAdapter
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView
import dev.itmo.rurevange.lab1.models.Post
import dev.itmo.rurevange.lab1.models.User

class MainActivity : AppCompatActivity() {

    private val posts = mutableListOf<Post>()
    private lateinit var user: User
    private lateinit var postAdapter: PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupMockData()
        setupUserProfile()
        setupPostsList()
    }

    private fun setupUserProfile() {
        val avatarImageView = findViewById<CircleImageView>(R.id.avatarImageView)
        val nameTextView = findViewById<TextView>(R.id.nameTextView)
        val usernameTextView = findViewById<TextView>(R.id.usernameTextView)
        val postsCountTextView = findViewById<TextView>(R.id.postsCountTextView)
        val followersCountTextView = findViewById<TextView>(R.id.followersCountTextView)
        val followingCountTextView = findViewById<TextView>(R.id.followingCountTextView)
        val followButton = findViewById<Button>(R.id.followButton)
        val messageButton = findViewById<Button>(R.id.messageButton)

        nameTextView.text = user.name
        usernameTextView.text = user.username
        postsCountTextView.text = user.postsCount.toString()
        followersCountTextView.text = user.followersCount.toString()
        followingCountTextView.text = user.followingCount.toString()

        Glide.with(this)
            .load(user.avatarUrl)
            .centerCrop()
            .into(avatarImageView)

        followButton.setOnClickListener {
            val message = if (!user.isFollowing) "Вы подписались на ${user.name}" else "Вы отписались от ${user.name}"
            user = user.copy(isFollowing = !user.isFollowing)
            followButton.text = if (user.isFollowing) "Отписаться" else "Подписаться"
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }

        messageButton.setOnClickListener {
            Toast.makeText(this, "Переход в \"личные сообщения\" с ${user.name}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupPostsList() {
        val recyclerView = findViewById<RecyclerView>(R.id.postsRecyclerView)

        postAdapter = PostAdapter(
            onLikeClick = { post ->
                val updatedPost = post.copy(
                    isLiked = !post.isLiked,
                    likesCount = if (!post.isLiked) post.likesCount + 1 else post.likesCount - 1
                )

                val position = posts.indexOfFirst { it.id == post.id }
                if (position != -1) {
                    posts[position] = updatedPost
                    postAdapter.submitList(posts.toList())
                }
                val message = if (updatedPost.isLiked) "Вы лайкнули пост" else "Вы дизлайкнули пост"
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            },
            onCommentClick = { post ->
                Toast.makeText(this, "Переход в комментарии поста", Toast.LENGTH_SHORT).show()
            }
        )

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = postAdapter
        }
        postAdapter.submitList(posts)
    }

    private fun setupMockData() {
        user = User(
            id = "1",
            name = "Иоанн Болтонов",
            username = "ioboltonov",
            avatarUrl = "https://img.freepik.com/premium-photo/animal-vector-with-character-wearing-jacket_575980-46712.jpg?w=2000",
            postsCount = 64,
            followersCount = 256,
            followingCount = 128
        )

        posts.addAll(listOf(
            Post(
                id = "1",
                text = "Perpetum Dominus Lux",
                imageUrl = "https://fastly.picsum.photos/id/12/3888/2592.jpg?hmac=z5QnvAxvFWTEDcrH9g34B5whrOlRpoyRMaX-wJpT9h0",
                likesCount = 123,
                commentsCount = 45
            ),
            Post(
                id = "2",
                text = "Сим салабим!",
                imageUrl = "https://fastly.picsum.photos/id/11/3888/2592.jpg?hmac=dkM-BSWi2m7YAH3n-fvvXSzUS4k668DYPBZ6UVoJN10",
                likesCount = 78,
                commentsCount = 23
            ),
            Post(
                id = "3",
                text = "Абра кадабра",
                imageUrl = "https://picsum.photos/id/237/800/500",
                likesCount = 215,
                commentsCount = 67,
                isLiked = true
            )
        ))
    }
}