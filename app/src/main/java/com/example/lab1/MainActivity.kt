package com.example.lab1

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var postAdapter: PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        recyclerView = findViewById(R.id.recyclerViewPosts)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.addItemDecoration(SpaceItemDecoration(16))
        postAdapter = PostAdapter(getSamplePosts())
        recyclerView.adapter = postAdapter


        val followButton: Button = findViewById(R.id.followButton)
        followButton.setOnClickListener {
            Toast.makeText(this, "Follow clicked", Toast.LENGTH_SHORT).show()
        }

        val messageButton: Button = findViewById(R.id.messageButton)
        messageButton.setOnClickListener {
            Toast.makeText(this, "Message clicked", Toast.LENGTH_SHORT).show()
        }

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener {
            Toast.makeText(this, "FAB clicked - create post", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getSamplePosts(): List<Post> {
        return listOf(
            Post("Первый пост", R.drawable.img1, 120),
            Post("Второй пост", R.drawable.img, 340),
            Post("Третий пост", R.drawable.img2, 50)
        )
    }
}
