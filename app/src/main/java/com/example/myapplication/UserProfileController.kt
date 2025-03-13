package com.example.myapplication

import android.content.res.ColorStateList
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide

class UserProfileController(private val user: User) {

    fun bind(view: View) {
        val userAvatar: ImageView = view.findViewById(R.id.profile_avatar)
        val userFullName: TextView = view.findViewById(R.id.profile_name)
        val userNickname: TextView = view.findViewById(R.id.nickname)
        val followersCount: TextView = view.findViewById(R.id.followers_count)
        val followingsCount: TextView = view.findViewById(R.id.followings_count)
        val postsCount: TextView = view.findViewById(R.id.posts_count)
        val followBtn: Button = view.findViewById(R.id.btn_follow)
        val sendMsgBtn: Button = view.findViewById(R.id.btn_send_message)

        userFullName.text = user.fullName
        userNickname.text = "@" + user.nickname
        if (user.imageSrc != null) {
            Glide.with(userAvatar.context).load(user.imageSrc).into(userAvatar)
        }

        followersCount.text = user.followersCount.toString()
        followingsCount.text = user.followingsCount.toString()
        postsCount.text = user.postsCount.toString()

        if (user.isFollowed) {
            followBtn.text = view.context.getString(R.string.btn_followed)
            val color = ContextCompat.getColor(view.context, R.color.btn_follow_clicked)
            followBtn.setBackgroundTintList(ColorStateList.valueOf(color))
        }

        followBtn.setOnClickListener {
            if (!user.isFollowed) {
                val color = ContextCompat.getColor(view.context, R.color.btn_follow_clicked)
                followBtn.setBackgroundTintList(ColorStateList.valueOf(color))
                user.isFollowed = true
                user.followersCount++
                followersCount.text = user.followersCount.toString()
                followBtn.text = view.context.getString(R.string.btn_followed)
            } else {
                val color = ContextCompat.getColor(view.context, R.color.btn_follow_default)
                followBtn.setBackgroundTintList(ColorStateList.valueOf(color))
                user.isFollowed = false
                user.followersCount--
                followersCount.text = user.followersCount.toString()
                followBtn.text = view.context.getString(R.string.btn_follow)
            }
        }

        sendMsgBtn.setOnClickListener {
            Toast.makeText(view.context, "New message sent", Toast.LENGTH_SHORT).show()
        }
    }
}