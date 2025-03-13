package com.example.myapplication

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val user = User(
            1,
            "Ailana Chimitova",
            "ailanachim",
            "https://sun9-20.userapi.com/impg/c857416/v857416301/20d93d/fa3Na6zbB9s.jpg?size=1215x2160&quality=96&sign=e3618713b8be549920009d1ecd95673c&type=album",
            12,
            3,
            3,
            true
        )

        val posts = listOf(
            Post(1, "Первый пост!", null, 5, false, 1),
            Post(
                2,
                "Второй пост!",
                "https://www.istockphoto.com/resources/images/PhotoFTLP/1040315976.jpg",
                30,
                true,
                10
            ),
            Post(3, "Третий пост!", null, 7, true, 5),
        )

        val userProfileController = UserProfileController(user)
        userProfileController.bind(findViewById(R.id.main))

        val recyclerView: RecyclerView = findViewById(R.id.recycler_posts)
        val postsViewAdapter = PostsViewAdapter(user, posts)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = postsViewAdapter
    }
}