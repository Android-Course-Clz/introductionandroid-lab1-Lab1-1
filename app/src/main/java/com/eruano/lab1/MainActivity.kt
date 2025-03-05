package com.eruano.lab1

import android.os.Bundle
import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.eruano.lab1.models.Post

class MainActivity : ComponentActivity() {
    private lateinit var postsRecyclerView: RecyclerView
    private lateinit var postAdapter: PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile);

        val profileImage = findViewById<ImageView>(R.id.profileImage)

        val imageUrl = "https://sun9-14.userapi.com/s/v1/ig2/hRllE48zX--fDv64boenpDHVyqNVpl4-Y8PnQZkOeznhGiEOJy8pwTJe76jKN5yA29QAE0l0QQkIkLAYhmeLy3cC.jpg?quality=96&crop=70,70,560,560&as=32x32,48x48,72x72,108x108,160x160,240x240,360x360,480x480,540x540&ava=1&u=e-E2LoSG40na_z-PBGeZ5P0kbyG-tJvMqcDkTWp-niA&cs=200x200"

        Glide.with(profileImage)
            .load(imageUrl)
            .placeholder(R.drawable.profile_placeholder)
            .error(R.drawable.error_placeholder)
            .into(profileImage)

        postsRecyclerView = findViewById(R.id.postsRecyclerView)


        postsRecyclerView.setLayoutManager(LinearLayoutManager(this))


        val postList = listOf(
            Post(
                "Заголовок 1",
                "Текст поста 1",
                "31 дек 2017",
                "https://dummyimage.com/600x300/000/fff&text=Hello+World"
            ),
            Post(
                "Заголовок 2",
                "Текст поста 2",
                "1 янв 2018",
                "https://dummyimage.com/600x300/ff0000/ffffff"
            ),
            Post(
                "Заголовок 3",
                "Текст поста 3",
                "2 янв 2018",
                "https://dummyimage.com/600x300/000/fff"
            )
        )


        postAdapter = PostAdapter(postList)

        postsRecyclerView.setAdapter(postAdapter)
    }
}