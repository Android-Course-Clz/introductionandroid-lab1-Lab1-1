package com.kosandron.socialnetworkprofile.ui

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kosandron.socialnetworkprofile.R
import com.kosandron.socialnetworkprofile.adapters.PostsAdapter
import com.kosandron.socialnetworkprofile.databinding.ActivityProfileBinding
import com.kosandron.socialnetworkprofile.models.Post
import com.kosandron.socialnetworkprofile.models.Profile
import java.util.Locale

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    private var isSubscribed = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val posts = listOf(
            Post(
                id = 1,
                urlImage = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRN1-CrQPeeSu6OSKWcOPMrjP2xjMCunbU7eQ&s",
                description = "My brothers",
                likesCount = 10,
                isLiked = false,
                commentsCount = 0
            ),
            Post(
                id = 2,
                urlImage = "https://images.techinsider.ru/upload/img_cache/9b5/9b56c003d032ee25521915f222270108_cropped_510x510.webp",
                description = "I`m tired.",
                likesCount = 20,
                isLiked = false,
                commentsCount = 0
            ),
            Post(
                id = 3,
                urlImage = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQLzkBDZJDC29iqQ6KUIT27YteprrXeLtCFvA&s",
                description = "In vacation",
                likesCount = 30,
                isLiked = false,
                commentsCount = 0
            )
        )
        val profile = Profile(
            name = "Cat",
            nickname = "@meowmeow",
            followersCount = 5,
            followingCount = 2,
            posts = posts,
            imageUrl = "https://img.freepik.com/premium-vector/vector-illustration-cute-happy-black-cat-with-green-eyes_172107-3448.jpg"
            //  imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS6r9H86IJxrG72T47jJVcikRzWqJl1-cdh9g&s"
        )
        loadProfile(profile)
    }

    private fun loadProfile(profile: Profile) {
        Glide.with(this)
            .load(profile.imageUrl)
            .apply(RequestOptions.circleCropTransform())
            .into(binding.avatarImageView)

        binding.nameTextView.text = profile.name
        binding.nicknameTextView.text = profile.nickname
        binding.followersCountTextView.text =
            String.format(Locale.US, "%,d", profile.followersCount)
        binding.followingCountTextView.text =
            String.format(Locale.US, "%,d", profile.followingCount)
        binding.postsCountTextView.text = String.format(Locale.US, "%,d", profile.posts.size)

        binding.subscribeButton.setOnClickListener {
            isSubscribed = !isSubscribed
            if (isSubscribed) {
                profile.followersCount++
                binding.subscribeButton.backgroundTintList = ColorStateList.valueOf(Color.GRAY)
                binding.subscribeButton.setText(R.string.Subscribed)
                binding.subscribeButton.setTextColor(Color.BLACK)
            } else {
                profile.followersCount--
                binding.subscribeButton.backgroundTintList =
                    ColorStateList.valueOf(Color.parseColor("#3CB2FC"))
                binding.subscribeButton.setText(R.string.SubscribeButtonText)
                binding.subscribeButton.setTextColor(Color.WHITE)
            }

            binding.followersCountTextView.text =
                String.format(Locale.US, "%,d", profile.followersCount)
        }

        binding.messageButton.setOnClickListener {
            Toast.makeText(this, "Open message fragment", Toast.LENGTH_SHORT).show()
        }
        loadPosts(profile.posts)
    }

    private fun loadPosts(posts: List<Post>) {

        val postsAdapter = PostsAdapter()
        binding.postsRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.postsRecyclerView.adapter = postsAdapter
        postsAdapter.differ.submitList(posts)
    }
}