package com.example.user_profile

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.user_profile.domain.User
import com.example.user_profile.domain.PostAdapter
import com.example.user_profile.service.TestDataLoader

/**
 * Профиль пользователя.
 */
class UserProfileActivity : ComponentActivity() {

    private lateinit var avatarImageView: ImageView
    private lateinit var firstNameTextView: TextView
    private lateinit var lastNameTextView: TextView
    private lateinit var usernameTextView: TextView
    private lateinit var followersCountTextView: TextView
    private lateinit var followingCountTextView: TextView
    private lateinit var postsCountTextView: TextView
    private lateinit var followButton: Button
    private lateinit var messageButton: Button
    private lateinit var postsRecyclerView: RecyclerView
    private val testDataLoader: TestDataLoader = TestDataLoader()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_profile_activity)
        enableEdgeToEdge()

        avatarImageView = findViewById(R.id.avatarImageView)
        firstNameTextView = findViewById(R.id.firstNameTextView)
        lastNameTextView = findViewById(R.id.lastNameTextView)
        usernameTextView = findViewById(R.id.usernameTextView)
        followersCountTextView = findViewById(R.id.followersCountTextView)
        followingCountTextView = findViewById(R.id.followingCountTextView)
        postsCountTextView = findViewById(R.id.postsCountTextView)
        followButton = findViewById(R.id.followButton)
        messageButton = findViewById(R.id.messageButton)
        postsRecyclerView = findViewById(R.id.postsRecyclerView)

        val user = testDataLoader.loadTestUser()
        bindUser(user)

        val adapter = PostAdapter()
        adapter.submitList(testDataLoader.loadPosts())
        postsRecyclerView.adapter = adapter

        followButton.setOnClickListener {
            onSubscribeClick(user)
        }

        messageButton.setOnClickListener {
            showMessageWindow()
        }
    }

    private fun bindUser(user: User) {
        Glide.with(this)
            .load(user.avatarUrl)
            .circleCrop()
            .error(R.drawable.error)
            .into(avatarImageView)

        firstNameTextView.text = user.firstName
        lastNameTextView.text = user.lastName
        usernameTextView.text = user.username
        followersCountTextView.text = getString(R.string.subscribers_count, user.subscribersCount)
        followingCountTextView.text = getString(R.string.subscribed_count, user.subscribedCount)
        postsCountTextView.text = getString(R.string.posts_count, user.postsCount)
    }

    private fun onSubscribeClick(user: User) {
        if (followButton.getText().toString().equals(getString(R.string.subscribe_button))) {
            followButton.setText(R.string.unsubscribe_button);
            followButton.setBackgroundResource(R.drawable.button_subscribed);
            user.subscribersCount++
            followersCountTextView.text = getString(R.string.subscribers_count, user.subscribersCount)
        } else {
            followButton.setText(R.string.subscribe_button);
            followButton.setBackgroundResource(R.drawable.button);
            user.subscribersCount--
            followersCountTextView.text = getString(R.string.subscribers_count, user.subscribersCount)
        }
    }

    private fun showMessageWindow() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.message_window, null)

        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .setTitle(R.string.message_window_title)
            .create()

        val sendButton: Button = dialogView.findViewById(R.id.sendButton)
        val messageInput: EditText = dialogView.findViewById(R.id.messageInput)

        sendButton.setOnClickListener {
            val message = messageInput.text.toString()
            if (message.isNotEmpty()) {
                Toast.makeText(this, "Сообщение успешно отправлено: $message", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Введите сообщение", Toast.LENGTH_SHORT).show()
            }
            dialog.dismiss()
        }

        dialog.show()
    }
}