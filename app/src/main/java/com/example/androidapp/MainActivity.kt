package com.example.androidapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ToggleButton
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.example.androidapp.databinding.ActivityMainBinding

class MainActivity : ComponentActivity() {
    lateinit var binding: ActivityMainBinding
    private val adapter = PostAdapter()
    private val imageUrlList = listOf(
        "https://i.pinimg.com/736x/dd/32/03/dd320372750ad35f3d488b50743135ed.jpg",
        "https://i.pinimg.com/736x/3e/a0/bd/3ea0bdce8bab2427078bcbe6bf8d92ae.jpg",
        "https://i.pinimg.com/736x/8c/8c/bc/8c8cbc4cd31d5065c931d839b7e19a9d.jpg",
        "https://i.pinimg.com/736x/68/dc/a9/68dca94c82879c61176cef0dbc9f2154.jpg",
        "https://i.pinimg.com/736x/88/83/b2/8883b2b55a79ab2f57a99fa302827fa1.jpg"
    )
    private var index = 0
    private var countPosts = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val imageView = binding.imageView

        Glide.with(this)
            .load("https://i.pinimg.com/736x/8f/20/87/8f20878be9378803ff39c99eb8b07f56.jpg")
            .circleCrop()
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_foreground)
            .into(imageView)

        val toggleButton: ToggleButton = binding.toggleButton
        toggleButton.setOnCheckedChangeListener { _, isChecked ->
            toggleButton.setBackgroundResource(if (isChecked) R.drawable.toggle_on else R.drawable.toggle_off)
        }

        init()
    }

    private fun init() {
        binding.apply {
            recyclerView.layoutManager = GridLayoutManager(this@MainActivity, 1)
            recyclerView.adapter = adapter

            for (i in 0 until 3) {
                if (i < imageUrlList.size) {
                    countPosts++
                    val post = Post(imageUrlList[i], "Пост $countPosts")
                    adapter.addPost(post)
                    index++
                }
            }

            updatePostCount()

            buttonAddPost.setOnClickListener {
                if (index >= imageUrlList.size) index = 0
                countPosts++
                val post = Post(imageUrlList[index], "Пост $countPosts")
                updatePostCount()
                adapter.addPost(post)
                index++
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun updatePostCount() {
        binding.textView9.text = countPosts.toString()
    }

}

