package ru.itmo.socialnetwork.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import ru.itmo.socialnetwork.R

fun ImageView.loadAvatar(imageUrl: String) {
    Glide.with(this.context)
        .load(imageUrl)
        .placeholder(R.drawable.image_placeholder_circle)
        .error(
            Glide.with(this.context)
                .load(R.drawable.default_avatar)
                .circleCrop()
        )
        .circleCrop()
        .into(this)
}

fun ImageView.loadImage(imageUrl: String) {
    Glide.with(this.context)
        .load(imageUrl)
        .placeholder(R.drawable.image_placeholder)
        .error(R.drawable.unavailable_image)
        .into(this)
}