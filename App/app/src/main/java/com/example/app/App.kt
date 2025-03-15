package com.example.app

import android.app.Application

class App : Application() {
    val postService = PostService()
}