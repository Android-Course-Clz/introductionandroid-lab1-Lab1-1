package com.example.myapplication.models

class Post (
    var id: Int = 0,
    var text: String = "test",
    var photo_url: String = "https://sun9-26.userapi.com/impg/6MCKmRm9yHbvLNqRr5M5cvNpMdHuQgQncWZjQQ/9flWjjIiTTE.jpg?size=2560x1920&quality=95&sign=8ca0022227ff3d39eacd9dae07003660&type=album",
    var comments: Int = 0,
    var likes: Int = 0,
    var liked: Boolean = false
) { }