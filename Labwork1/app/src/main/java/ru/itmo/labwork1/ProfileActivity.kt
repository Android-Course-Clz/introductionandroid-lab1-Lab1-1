package ru.itmo.labwork1

import android.content.ClipData
import android.content.ClipboardManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import ru.itmo.labwork1.adapters.PostsAdapter
import ru.itmo.labwork1.databinding.ProfileActivityBinding
import ru.itmo.labwork1.models.Post

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ProfileActivityBinding
    private lateinit var adapter: PostsAdapter
    private var isSubscribed = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ProfileActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.userNicknameTextView.setOnClickListener {
            copyNicknameToClipboard("https://t.me/${binding.userNicknameTextView.text.substring(1)}")
        }
        binding.subscribeButton.setOnClickListener {
            if (isSubscribed) {
                updateFollowersCount(-1)
                binding.subscribeButton.setImageResource(R.drawable.subscribe)
            } else {
                updateFollowersCount(1)
                binding.subscribeButton.setImageResource(R.drawable.followed)
            }
            isSubscribed = !isSubscribed
        }

        Glide.with(this)
            .load("https://distribution.faceit-cdn.net/images/93d54b2b-0b22-4cf2-8c5d-40d6d419fe74.jpeg")
            .placeholder(R.drawable.ic_placeholder_avatar)
            .override(200, 200)
            .circleCrop()
            .into(binding.avatarImageView)

        adapter = PostsAdapter()
        binding.postsRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.postsRecyclerView.adapter = adapter

        val dummyPosts = listOf(
            Post("Непосредственно кот непосредственно лежит", "https://i.pinimg.com/originals/1a/c4/81/1ac4818d4729d4a39cdda4462e12a5b3.jpg", 10, 5),
            Post("Дамблдор поджёг Поттера и почувствовал запах Гарри \uD83D\uDE01", null, 8, 2),
            Post("", "https://sun9-13.userapi.com/impg/gajo20WIwQblT2tTVnBLPyAJBtFjdQ2QmXd21g/-zYZ4exYFtY.jpg?size=890x980&quality=95&sign=da9e811d067077da014d49981834a1fe&type=album", 3, 1),
            Post("Post 4", "", 123, 123),
            Post("Post 5", null, 3, 1),
            Post("Post 6", "", 123, 123),
        )

        adapter.submitList(dummyPosts)
        updatePostsCount(dummyPosts.size)
    }

    private fun updatePostsCount(count: Int) {
        binding.postsCountTextView.text = "$count"
    }

    private fun updateFollowersCount(delta: Int) {
        val currentFollowers = binding.followersCountTextView.text.toString().toInt()
        val newFollowers = currentFollowers + delta
        binding.followersCountTextView.text = newFollowers.toString()
    }

    private fun updateFollowingCount(count: Int) {
        binding.subscriptionsCountTextView.text = "$count"
    }

    private fun copyNicknameToClipboard(link: String) {
        val clipboardManager = binding.root.context.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
        val clipData = ClipData.newPlainText("Profile Link", link)
        clipboardManager.setPrimaryClip(clipData)

        Toast.makeText(binding.root.context, "Ссылка скопирована в буфер обмена", Toast.LENGTH_SHORT).show()
    }
}