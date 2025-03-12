package com.zcchr.intro

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var postAdapter: PostAdapter
    private val allPosts = mutableListOf<Post>()
    private var isSubscribed = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewPosts)
        recyclerView.layoutManager = LinearLayoutManager(this)

        allPosts.addAll(getPosts())

        postAdapter = PostAdapter()
        recyclerView.adapter = postAdapter
        postAdapter.submitList(allPosts.toList())

        val btnFollow = findViewById<Button>(R.id.btnFollow)
        btnFollow.setOnClickListener {
            isSubscribed = !isSubscribed
            updateFollowButtonText(btnFollow)
        }

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
                val totalItemCount = layoutManager.itemCount

                if (lastVisibleItemPosition == totalItemCount - 1) {
                    val newPosts = getPosts()
                    allPosts.addAll(newPosts)
                    postAdapter.submitList(allPosts.toList())
                }
            }
        })
    }

    fun getPosts(): List<Post> {
        val newPosts = mutableListOf<Post>()

        (0..5).forEach { i ->
            val currentId = allPosts.size + newPosts.size + 1

            newPosts.add(
                Post(
                    id = currentId,
                    name = "user${currentId}",
                    username = "@user${currentId}",
                    likesCount = Random.nextInt(100),
                    commentsCount = Random.nextInt(50),
                    textContent = "This is post $currentId",
                    imageUrl = "https://dcblog.b-cdn.net/wp-content/uploads/2021/02/Full-form-of-URL-1.jpg"
                )
            )
        }

        return newPosts
    }

    private fun updateFollowButtonText(button: Button) {
        if (isSubscribed) {
            button.text = getString(R.string.unsubscribe)
        } else {
            button.text = getString(R.string.subscribe)
        }
    }
}
