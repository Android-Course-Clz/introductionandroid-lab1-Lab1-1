package ru.itmo.lab1_konstantinfrantz

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var postAdapter: PostAdapter
    private lateinit var btnFollow: Button
    private lateinit var btnLoadMore: Button
    private var isFollowing = false
    private val allPosts = mutableListOf<Post>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        initUI()

        setupRecyclerView()
    }

    private fun initUI() {
        val ivHeaderBackground = findViewById<ImageView>(R.id.ivHeaderBackground)
        Glide.with(this)
            .load("https://img.freepik.com/premium-photo/big-tasty-burger-plate-patriotic-cafe-with-american-flag-background-healthy_960396-345510.jpg")
            .centerCrop()
            .into(ivHeaderBackground)

        val ivAvatar = findViewById<ImageView>(R.id.ivAvatar)
        Glide.with(this)
            .load("https://substackcdn.com/image/fetch/f_auto,q_auto:good,fl_progressive:steep/https%3A%2F%2Fsubstack-post-media.s3.amazonaws.com%2Fpublic%2Fimages%2F354aea6d-b752-4b05-88cf-ce27d41bd861_548x560.jpeg")
            .apply(RequestOptions.circleCropTransform())
            .into(ivAvatar)

        findViewById<TextView>(R.id.tvName).text = getString(R.string.profile_name)
        findViewById<TextView>(R.id.tvNickname).text = getString(R.string.profile_nickname)

        findViewById<TextView>(R.id.tvFollowersCount).text = getString(R.string.profile_followers)
        findViewById<TextView>(R.id.tvFollowingCount).text = getString(R.string.profile_following)

        btnFollow = findViewById(R.id.btnFollow)
        btnFollow.setOnClickListener {
            isFollowing = !isFollowing
            if (isFollowing) {
                btnFollow.text = getString(R.string.btn_unfollow)
            } else {
                btnFollow.text = getString(R.string.btn_follow)
            }
        }

        findViewById<ImageButton>(R.id.btnMessage).setOnClickListener {
            Toast.makeText(this, getString(R.string.toast_message), Toast.LENGTH_SHORT).show()
        }

        btnLoadMore = findViewById(R.id.btnLoadMore)
        btnLoadMore.setOnClickListener {
            loadMorePosts()
        }
    }

    private fun setupRecyclerView() {
        recyclerView = findViewById(R.id.rvPosts)
        recyclerView.layoutManager = LinearLayoutManager(this)

        allPosts.addAll(createDummyPosts())

        val initialPosts = allPosts.take(3).toMutableList()

        postAdapter = PostAdapter(initialPosts)
        recyclerView.adapter = postAdapter

        findViewById<TextView>(R.id.tvPostsCount).text = String.format("%d", initialPosts.size)
    }

    private fun createDummyPosts(): List<Post> {
        return listOf(
            Post(
                id = 1,
                authorName = "Tonald Drump",
                authorNickname = "@realTonaldDrump",
                authorAvatarUrl = "https://substackcdn.com/image/fetch/f_auto,q_auto:good,fl_progressive:steep/https%3A%2F%2Fsubstack-post-media.s3.amazonaws.com%2Fpublic%2Fimages%2F354aea6d-b752-4b05-88cf-ce27d41bd861_548x560.jpeg",
                text = "Support me on OnlyFans:\nhttps://onlyfans.com/TonaldDrump",
                imageUrl = null,
                likeCount = 358700,
                commentCount = 45200,
                isLiked = false
            ),
            Post(
                id = 2,
                authorName = "Tonald Drump",
                authorNickname = "@realTonaldDrump",
                authorAvatarUrl = "https://substackcdn.com/image/fetch/f_auto,q_auto:good,fl_progressive:steep/https%3A%2F%2Fsubstack-post-media.s3.amazonaws.com%2Fpublic%2Fimages%2F354aea6d-b752-4b05-88cf-ce27d41bd861_548x560.jpeg",
                text = "CHINA, CHINA, CHINA",
                imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSrUMxEmFJhuYak_L0H8o5HQO7scUKeYsKUcA&s",
                likeCount = 246900,
                commentCount = 31500,
                isLiked = false
            ),
            Post(
                id = 3,
                authorName = "Tonald Drump",
                authorNickname = "@realTonaldDrump",
                authorAvatarUrl = "https://substackcdn.com/image/fetch/f_auto,q_auto:good,fl_progressive:steep/https%3A%2F%2Fsubstack-post-media.s3.amazonaws.com%2Fpublic%2Fimages%2F354aea6d-b752-4b05-88cf-ce27d41bd861_548x560.jpeg",
                text = "Kanye just dropped new merch!",
                imageUrl = "https://i.redd.it/90bun9g8z9tc1.jpeg",
                likeCount = 10,
                commentCount = 25700,
                isLiked = false
            ),
            Post(
                id = 4,
                authorName = "Tonald Drump",
                authorNickname = "@realTonaldDrump",
                authorAvatarUrl = "https://substackcdn.com/image/fetch/f_auto,q_auto:good,fl_progressive:steep/https%3A%2F%2Fsubstack-post-media.s3.amazonaws.com%2Fpublic%2Fimages%2F354aea6d-b752-4b05-88cf-ce27d41bd861_548x560.jpeg",
                text = "Canada awaits",
                imageUrl = "https://www.washingtonpost.com/wp-apps/imrs.php?src=https://arc-anglerfish-washpost-prod-washpost.s3.amazonaws.com/public/GGSGPWEK3II6ZA4PBT67NHGOHQ.jpg&w=1440&impolicy=high_res",
                likeCount = 412500,
                commentCount = 51800,
                isLiked = false
            ),
            Post(
                id = 5,
                authorName = "Tonald Drump",
                authorNickname = "@realTonaldDrump",
                authorAvatarUrl = "https://substackcdn.com/image/fetch/f_auto,q_auto:good,fl_progressive:steep/https%3A%2F%2Fsubstack-post-media.s3.amazonaws.com%2Fpublic%2Fimages%2F354aea6d-b752-4b05-88cf-ce27d41bd861_548x560.jpeg",
                text = "Had the most beautiful chocolate cake at dinner - everybody says it's the best cake they've ever had. The chef learned everything from me. I know more about cake than anyone, believe me!",
                imageUrl = null,
                likeCount = 65,
                commentCount = 37200,
                isLiked = false
            ),
            Post(
                id = 6,
                authorName = "Tonald Drump",
                authorNickname = "@realTonaldDrump",
                authorAvatarUrl = "https://substackcdn.com/image/fetch/f_auto,q_auto:good,fl_progressive:steep/https%3A%2F%2Fsubstack-post-media.s3.amazonaws.com%2Fpublic%2Fimages%2F354aea6d-b752-4b05-88cf-ce27d41bd861_548x560.jpeg",
                likeCount = 325600,
                text = "URGENT!!!\n\nGo buy our new memecoin!",
                imageUrl = "https://img.asmedia.epimg.net/resizer/v2/EHEJP5XNFRDURFZJF6ADGS6YWQ.jpg?auth=c8020cfb97d82633aa93028fa9ca412b9b09604bc10967867f1c87bf68a75df6&width=976&height=549&smart=true",
                commentCount = 42300,
                isLiked = false
            ),
            Post(
                id = 7,
                authorName = "Tonald Drump",
                authorNickname = "@realTonaldDrump",
                authorAvatarUrl = "https://substackcdn.com/image/fetch/f_auto,q_auto:good,fl_progressive:steep/https%3A%2F%2Fsubstack-post-media.s3.amazonaws.com%2Fpublic%2Fimages%2F354aea6d-b752-4b05-88cf-ce27d41bd861_548x560.jpeg",
                likeCount = 187400,
                text = "Remember our latest trip to Japan? That was a banger!",
                imageUrl = "https://i0.wp.com/epthinktank.eu/wp-content/uploads/2018/01/eprs-briefing-608720-understanding-nuclear-weapons-ballistic-missiles-final.jpg?fit=1000%2C721&ssl=1",
                commentCount = 29800,
                isLiked = false
            )
        )
    }

    private fun loadMorePosts() {
        val randomCount = Random.nextInt(3, 6)
        val randomPosts = allPosts.shuffled().take(randomCount)

        postAdapter.updatePosts(randomPosts)

        findViewById<TextView>(R.id.tvPostsCount).text = String.format("%d", randomPosts.size)

        btnLoadMore.text = getString(R.string.btn_show_other_posts)
    }
}