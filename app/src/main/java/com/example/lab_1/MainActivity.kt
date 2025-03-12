package com.example.lab_1

import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lab_1.models.Post
import com.example.lab_1.models.User
import com.example.lab_1.repositories.CommentsRepository
import com.example.lab_1.repositories.UsersRepository


class MainActivity : ComponentActivity() {
    private lateinit var postAdapter: PostAdapter
    private lateinit var rvPostsList: RecyclerView
    private lateinit var usersRepository: UsersRepository
    private lateinit var commentsRepository: CommentsRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val initialPosts = listOf(
            Post(
                id = 1,
                image = "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e5/Bloemknoppen_van_een_vlier_%28Sambucus_serenade%29._14-04-2021_%28actm.%29_01.jpg/1000px-Bloemknoppen_van_een_vlier_%28Sambucus_serenade%29._14-04-2021_%28actm.%29_01.jpg",
                description = "Оцените цветок из Википедии",
                likes = 10
            ),
            Post(
                id = 2,
                image = "https://aif-s3.aif.ru/images/011/082/4fab8961c87e9f125fceda88bbbbcebc.jpg",
                description = "На днях Посмотрел фильм этого веселого парня. " +
                        "Немного сюжета: Терзаемый хронической бессонницей и отчаянно пытающийся вырваться из мучительно скучной жизни клерк встречает некоего Тайлера Дардена, харизматического торговца мылом с извращенной философией. Тайлер уверен, что самосовершенствование — удел слабых, а саморазрушение — единственное, ради чего стоит жить.",
                likes = 2
            ),
            Post(
                id = 3, image = "https://animals-travel.ru/upload/medialibrary/693/693042430520412f7d94b7a6886799c3.jpg",
                description = "Я когда жду фулл бал за первую лабу по Андройду^^",
                likes = 1
            )

        )

        val initialUsers = listOf(
            User(
                1,
                "https://i1.sndcdn.com/avatars-000701366305-hu9f0i-t1080x1080.jpg",
                "Max",
                "ejatohvee",
                3,
                123,
                initialPosts.size
            )
        )

        usersRepository = UsersRepository(initialUsers)
        commentsRepository = CommentsRepository()

        val ivAvatar = findViewById<ImageView>(R.id.ivAvatar)
        val tvName = findViewById<TextView>(R.id.tvName)
        val tvUsername = findViewById<TextView>(R.id.tvUsername)
        val tvProfileStats = findViewById<TextView>(R.id.tvProfileStats)
        val ibSubscribeButton = findViewById<ImageButton>(R.id.ibSubscribeButton)
        val ibMessageButton = findViewById<ImageButton>(R.id.ibMessageButton)

        val currentUser = usersRepository.users.first()

        tvName.text = currentUser.name
        tvUsername.text = currentUser.username
        tvProfileStats.text = "Подписчиков: ${currentUser.subscribersAmount}  •  " +
                "Подписок: ${currentUser.subscribesAmount}  •  " +
                "Постов: ${currentUser.postsAmount}"
        Glide.with(this)
            .load(currentUser.photo)
            .into(ivAvatar)
        ibSubscribeButton.setOnClickListener {
            currentUser.subscribersAmount += 1;
            tvProfileStats.text = "Подписчиков: ${currentUser.subscribersAmount}  •  " +
                    "Подписок: ${currentUser.subscribesAmount}  •  " +
                    "Постов: ${initialPosts.size}"
        }

        postAdapter = PostAdapter(
            initialPosts,
            onLikeClick = { post ->
                val newList = postAdapter.posts.map {
                    if (it.id == post.id) it.copy(likes = it.likes + 1) else it
                }
                postAdapter.updateList(newList)
            }, commentsRepository
        )

        rvPostsList = findViewById(R.id.rvPostsList)
        rvPostsList.layoutManager = LinearLayoutManager(this)
        rvPostsList.adapter = postAdapter

    }
}
