package ru.aroize.lab_1


import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity(){

    private lateinit var profilePicture: ImageView
    private lateinit var subscribeButton: Button
    private lateinit var showPostsButton: Button
    private lateinit var amountOfPosts: TextView
    private lateinit var backgroundImage: ConstraintLayout
    private lateinit var header: ConstraintLayout
    private lateinit var friends: ConstraintLayout
    private var isSubscribed = false
    private var isHiddenPosts = true
    private val numOfPosts = 20


    override fun onCreate(savedInstanceState :  Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        profilePicture = findViewById(R.id.profilePicture)
        Glide.with(this)
            .load("https://avatars.mds.yandex.net/i?id=e9e3669667ad73355e8f34a3dab3133c63725a71-5315630-images-thumbs&n=13")
            .placeholder(R.drawable.comment_button)
            .error(R.drawable.like_button_pressed)
            .into(profilePicture)

        subscribeButton = findViewById(R.id.subscribeButton)
        subscribeButton.setOnClickListener{
            if (isSubscribed) {
                subscribeButton.text = getString(R.string.subscribeButtonText)
                isSubscribed = false
            } else {
                subscribeButton.text = getString(R.string.subscribedButtonText)
                isSubscribed = true
            }
        }

        backgroundImage = findViewById(R.id.backgroundImageLayout)
        header = findViewById(R.id.headerLayout)
        friends = findViewById(R.id.friendsLayout)
        showPostsButton = findViewById(R.id.showPostsButton)
        showPostsButton.setOnClickListener{
            if (isHiddenPosts) {
                showPostsButton.text = getString(R.string.showPosts)
                backgroundImage.visibility = View.VISIBLE
                header.visibility = View.VISIBLE
                friends.visibility = View.VISIBLE
                isHiddenPosts = false
            } else {
                showPostsButton.text = getString(R.string.hidePosts)
                backgroundImage.visibility = View.GONE
                header.visibility = View.GONE
                friends.visibility = View.GONE
                isHiddenPosts = true
            }
        }

        val recyclerview: RecyclerView = findViewById(R.id.recyclerview)

        recyclerview.layoutManager = LinearLayoutManager(this)



        val data = ArrayList<Item>()
        var counter = 1
        for (i in 1..numOfPosts) {
            data.add(Item(
                counter,
                "https://avatars.mds.yandex.net/i?id=e9e3669667ad73355e8f34a3dab3133c63725a71-5315630-images-thumbs&n=13",
                R.drawable.ic_launcher_foreground,
                "Item $counter",
                "Ваш комментарий",
                0,
                false,
                0))
            counter++
        }

        val adapter = Adapter(data)

        recyclerview.adapter = adapter

        amountOfPosts = findViewById(R.id.amountOfPosts)
        amountOfPosts.text = counter.toString() + " постов"
        val newData = data.toMutableList().apply {
            add(Item(counter,
                "https://avatars.mds.yandex.net/i?id=e9e3669667ad73355e8f34a3dab3133c63725a71-5315630-images-thumbs&n=13",
                R.drawable.ic_launcher_foreground,
                "New Item",
                "Новый комментарий",
                0,
                false,
                0))
        }
        adapter.updateData(newData)
        amountOfPosts.text = counter.toString() + " постов"
        counter++
    }
}