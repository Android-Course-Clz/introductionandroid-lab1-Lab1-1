package com.chtonad0000.profileapp

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MainActivity : ComponentActivity() {

    private lateinit var adapter: PostAdapter
    private val posts = mutableListOf(
        Post(1, "Пляж", "https://i.pinimg.com/736x/4a/6e/65/4a6e6598e8c9c1389d6b10fd08d836ab.jpg", 10, 2),
        Post(2, "Горы", "https://i.pinimg.com/736x/b3/c1/be/b3c1be4f20985e278e6a47d8718ef9e0.jpg", 5, 1),
        Post(3, "Озеро", "https://i.pinimg.com/736x/bb/71/3d/bb713d5897c65ce91afde1f9732633d0.jpg", 8, 3)
    )

    private var isFriend = false
    private var friendsCount = 46

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val avatarImageView = findViewById<ImageView>(R.id.avatar)
        Glide.with(applicationContext)
            .load("https://i.pinimg.com/originals/d5/3c/e2/d53ce2a5c7565dd08064368f6458eede.jpg")
            .circleCrop()
            .placeholder(R.drawable.avatar_placeholder)
            .into(avatarImageView)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_posts)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = PostAdapter()
        recyclerView.adapter = adapter
        adapter.submitList(posts)

        val btnAddFriend = findViewById<Button>(R.id.btn_add_friend)
        val friendsCountText = findViewById<TextView>(R.id.friends_count)

        friendsCountText.text = friendsCount.toString()

        btnAddFriend.setOnClickListener {
            isFriend = !isFriend

            if (isFriend) {
                friendsCount++
                btnAddFriend.text = "Удалить из друзей"
                btnAddFriend.isSelected = true
            } else {
                friendsCount--
                btnAddFriend.text = "Добавить в друзья"
                btnAddFriend.isSelected = false
            }

            friendsCountText.text = friendsCount.toString()
        }
    }
}
