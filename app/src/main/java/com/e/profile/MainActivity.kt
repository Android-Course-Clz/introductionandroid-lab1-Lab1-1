package com.e.profile

import android.content.res.Configuration
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class MainActivity : AppCompatActivity() {

    private lateinit var subscribeButton: ImageButton
    private lateinit var recyclerView: RecyclerView
    private lateinit var postAdapter: PostAdapter
    private lateinit var main: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        subscribeButton = findViewById(R.id.follow)
        var isSubscribed = false
        subscribeButton.setOnClickListener {
            if (isSubscribed) {
                subscribeButton.setImageResource(R.drawable.subscribe)
            } else {
                subscribeButton.setImageResource(R.drawable.subscribed)
            }
            isSubscribed = !isSubscribed
        }

        Glide.with(this)
            .load(getString(R.string.picture_url))
            .into(findViewById(R.id.avatar))

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.setLayoutManager(LinearLayoutManager(this))

        val posts: MutableList<Post> = ArrayList()
        posts.add(
            Post(getString(R.string.post_text),
                getString(R.string.picture_url),
                getResources().getInteger(R.integer.likes_number),
                getResources().getInteger(R.integer.comment_number)))
        posts.add(
            Post(getString(R.string.post_text),
                null,
                getResources().getInteger(R.integer.likes_number),
                getResources().getInteger(R.integer.comment_number)))
        posts.add(
            Post(getString(R.string.post_text),
                null,
                getResources().getInteger(R.integer.likes_number),
                getResources().getInteger(R.integer.comment_number)))

        postAdapter = PostAdapter(posts)
        recyclerView.setAdapter(postAdapter)

        main = findViewById(R.id.main)
        applyConstraints(getResources().configuration.orientation)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        applyConstraints(newConfig.orientation)
    }

    private fun applyConstraints(orientation: Int) {
        val constraintSet = ConstraintSet()
        constraintSet.clone(main)

        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            constraintSet.setHorizontalBias(R.id.follow, 0.948f)
            constraintSet.setVerticalBias(R.id.follow, 0.025f)
            constraintSet.setHorizontalBias(R.id.message, 0.700f)
            constraintSet.setVerticalBias(R.id.message, 0.025f)
            constraintSet.connect(
                R.id.recyclerView,
                ConstraintSet.TOP,
                R.id.guideline_horizontal,
                ConstraintSet.BOTTOM
            )
            constraintSet.connect(
                R.id.recyclerView,
                ConstraintSet.START,
                main.id,
                ConstraintSet.START
            )
        } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            constraintSet.setHorizontalBias(R.id.follow, 0.428f)
            constraintSet.setVerticalBias(R.id.follow, 0.047f)
            constraintSet.setHorizontalBias(R.id.message, 0.289f)
            constraintSet.setVerticalBias(R.id.message, 0.047f)
            constraintSet.connect(
                R.id.recyclerView,
                ConstraintSet.START,
                R.id.guideline_vertical,
                ConstraintSet.END
            )
            constraintSet.connect(
                R.id.recyclerView,
                ConstraintSet.TOP,
                main.id,
                ConstraintSet.TOP
            )
        }

        constraintSet.applyTo(main)
    }
}