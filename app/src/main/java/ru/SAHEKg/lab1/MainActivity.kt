package ru.SAHEKg.lab1

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val avatarLink: String = "https://3dmodels.org/wp-content/uploads/2020/08/17/among_us_crewmate_3d_model_1000_0001.jpg"

        val avatarBkg: ImageView = findViewById(R.id.avatarBackground)
        Glide.with(this)
            .load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTSm8XbcESI11GNUDFjFvEyDTdirBkOieEK5Q&s")
            .placeholder(R.drawable.ic_launcher_background)
            .into(avatarBkg)

        val avatar: ImageView = findViewById(R.id.avatar)
        Glide.with(this)
            .load(avatarLink)
            .placeholder(R.drawable.ic_android_black_24dp)
            .circleCrop()
            .into(avatar)

        var isFollowing = false
        val buttonFollow: Button = findViewById(R.id.buttonFollow)
        buttonFollow.setOnClickListener {
            isFollowing = !isFollowing
            buttonFollow.text = if (isFollowing) "Вы подписаны" else "Подписаться"
        }

        val posts: RecyclerView = findViewById(R.id.post_list)
        val postData = listOf(
            Post(avatarLink, "Имя пользователя", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS_W3DROWqNiuGJJDf0cPFdj4lkY849DMe33g&s", "Контент первого поста"),
            Post(avatarLink, "Имя пользователя", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR5oR3qCK7IunWEEH9zLaSbWesbrdeeiYoarA&s", "Контент второго поста")
        )
        posts.layoutManager = LinearLayoutManager(this)
        posts.adapter = PostAdapter(postData)
    }
}