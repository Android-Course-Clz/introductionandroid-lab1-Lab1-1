package com.example.myapplication

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.core.graphics.toColorInt
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.MainActivityBinding
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.adaptors.PostsAdapter
import com.example.myapplication.models.Post

class MainActivity : ComponentActivity() {
    lateinit var binding : MainActivityBinding;
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PostsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getAllImgs()
        initRecycleView()

        binding.AddFriend.setOnClickListener {
            handleFriendRequest()
        }
    }

    fun handleFriendRequest() {
        val temp = binding.friendsNumber.text.toString().split(' ');
        if(binding.AddFriend.text == "Добавить в друзья") {
            binding.AddFriend.text = "Добавлен в друзья"
            binding.friendsNumber.text = (temp[0].toInt() + 1).toString() + " " + temp[1]
        } else {
            binding.AddFriend.text = "Добавить в друзья"
            binding.friendsNumber.text = (temp[0].toInt() - 1).toString() + " " + temp[1]
        }
    }

    fun initRecycleView() {
        recyclerView = binding.recyclerView
        adapter = PostsAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        var postsList: List<Post> = listOf(
            Post(0, "what a cute cat", "https://wallpapers.com/images/high/cute-cats-pictures-ql9944oy268yhkzw.webp", 4, 200),
            Post(1, "look at this kitty", "https://img.freepik.com/free-photo/cute-cat-spending-time-indoors_23-2150649131.jpg?ga=GA1.1.1623471989.1741784853", 3, 300),
            Post(1, "I love this one!", "https://img.freepik.com/free-photo/woman-holding-her-adorable-kitty-home_23-2149167141.jpg?ga=GA1.1.1623471989.1741784853", 0, 158),
        );
        binding.postCount.text = "Постов: " + postsList.size.toString()
        adapter.differ.submitList(postsList)
    }

    fun getAllImgs() {
        val avatarUrl = "https://sun9-36.userapi.com/impg/Ih1P7krGRcuzTvnjhsU5g7Kxf2Hy3expAlkfXQ/FkNfDYVXek8.jpg?size=1627x2160&quality=95&sign=c8b121da6b7e41b4cdfec7b14ddb58aa&type=album"
        Glide.with(this)
            .load(avatarUrl)
            .circleCrop()
            .into(binding.avatar)

        val firstImageUrl = "https://sun9-36.userapi.com/impg/Ih1P7krGRcuzTvnjhsU5g7Kxf2Hy3expAlkfXQ/FkNfDYVXek8.jpg?size=1627x2160&quality=95&sign=c8b121da6b7e41b4cdfec7b14ddb58aa&type=album"
        Glide.with(this)
            .load(firstImageUrl)
            .override(300, 300)
            .centerCrop()
            .into(binding.imgF)

        val secondImageUrl = "https://sun9-41.userapi.com/impg/Q5RrV6Y2T1do61H1sWjq1J8TZUKC_F0PpUdwVQ/bFAOCvNYJtE.jpg?size=2560x1511&quality=95&sign=54679e4128685b728285a421ea1309dc&type=album"
        Glide.with(this)
            .load(secondImageUrl)
            .override(300, 300)
            .centerCrop()
            .into(binding.imgS)

        val thirdImageUrl = "https://sun9-26.userapi.com/impg/6MCKmRm9yHbvLNqRr5M5cvNpMdHuQgQncWZjQQ/9flWjjIiTTE.jpg?size=2560x1920&quality=95&sign=8ca0022227ff3d39eacd9dae07003660&type=album"
        Glide.with(this)
            .load(thirdImageUrl)
            .override(300, 300)
            .centerCrop()
            .into(binding.imgT)
    }
}
