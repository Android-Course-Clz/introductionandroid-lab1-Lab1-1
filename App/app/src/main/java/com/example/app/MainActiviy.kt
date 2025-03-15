package com.example.app

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.app.databinding.MainActivityBinding
import java.io.BufferedInputStream
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding
    private lateinit var adapter: PostsAdapter
    private lateinit var postService: PostService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        postService = PostService()

        val mWebPath = "https://images.unsplash.com/photo-1600267185393-e158a98703de?crop=entropy&cs=tinysrgb&fit=crop&fm=jpg&h=600&ixid=MnwxfDB8MXxyYW5kb218fHx8fHx8fHwxNjI0MDE0NjQ0&ixlib=rb-1.2.1&q=80&utm_campaign=api-credit&utm_medium=referral&utm_source=unsplash_source&w=800"
        Glide.with(this)
            .load(mWebPath)
            .circleCrop()
            .error(R.drawable.avatar_photo)
            .into(binding.avatar)

        val manager = LinearLayoutManager(this)
        adapter = PostsAdapter(object: PostActionListener {
            override fun onPostLike(post: Post) = postService.likePost(post)

            override fun onPostComment(post: Post) = postService.addComment(post)
        })

        postService.addListener(listener)
        binding.recyclerView.layoutManager = manager
        binding.recyclerView.adapter = adapter
    }

    private val listener: PostListener = { adapter.data = it }
}