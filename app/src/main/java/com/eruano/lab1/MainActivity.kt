package com.eruano.lab1

import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.eruano.lab1.models.Post


class MainActivity : ComponentActivity() {
    private lateinit var postsRecyclerView: RecyclerView
    private lateinit var postAdapter: PostAdapter
    private lateinit var friendsCountTextView: TextView

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


        friendsCountTextView = findViewById(R.id.friendsCount)

        updateFriendsCount(10, 2)

        postsRecyclerView = findViewById(R.id.postsRecyclerView)

        postsRecyclerView.setLayoutManager(LinearLayoutManager(this))


        val postList = listOf(
            Post(
                "Заголовок 1",
                "Текст поста 1",
                "31 дек 2017",
                "https://dummyimage.com/600x300/000/fff&text=Hello+World",
                0
            ),
            Post(
                "Заголовок 2",
                "Текст поста 2",
                "1 янв 2018",
                "https://dummyimage.com/600x300/ff0000/ffffff",
                0
            ),
            Post(
                "Заголовок 3",
                "Текст поста 3",
                "2 янв 2018",
                "https://dummyimage.com/600x300/000/fff",
                0
            )
        )

        postAdapter = PostAdapter(postList)

        postsRecyclerView.setAdapter(postAdapter)
    }

    private fun updateFriendsCount(friendsCount: Int, requestsCount: Int) {
        val friendsText = "Друзей: $friendsCount"
        val requestsText = "Заявок: $requestsCount"
        val fullText = "$friendsText · $requestsText"

        val spannableString = SpannableString(fullText)
        spannableString.setSpan(
            ForegroundColorSpan(Color.BLACK),
            0,
            friendsText.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        spannableString.setSpan(
            ForegroundColorSpan(Color.BLUE),
            friendsText.length,
            fullText.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        friendsCountTextView.text = spannableString
    }
}