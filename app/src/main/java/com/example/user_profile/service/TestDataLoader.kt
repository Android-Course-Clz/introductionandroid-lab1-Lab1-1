package com.example.user_profile.service

import com.example.user_profile.domain.Post
import com.example.user_profile.domain.User

/**
 * Класс для загрузки тестовых данных.
 */
class TestDataLoader {
    fun loadTestUser(): User {
        return User(
            avatarUrl = "https://drive.google.com/uc?export=download&id=12NefZc-Pre7xCASkNGlv_a4N5qy8IZNx",
            firstName = "Елизавета",
            lastName = "Котельникова",
            username = "@elizavetakotelnikova",
            subscribersCount = 1000,
            subscribedCount = 330,
            postsCount = 50
        )
    }

    fun loadPosts(): List<Post> {
        return listOf(
            Post(
                id = 1,
                description = "Сегодня приехали в Токио!",
                imageUrl = "https://drive.google.com/uc?export=download&id=1YucV99TEco6G88uud14WeQwMcYsqRy3-",
                likesCount = 200,
                commentsCount = 10
            ),
            Post(
                id = 2,
                description = "Завтра едем в Киото)",
                imageUrl = "https://drive.google.com/uc?export=download&id=11qwyYfSUdAXPTU5N16CcsQkEmosmOPjT",
                likesCount = 50,
                commentsCount = 5
            ),
            Post(
                id = 3,
                description = "Еще ездили посмотреть на Фудзи. Очень понравилось!!",
                imageUrl = "https://drive.google.com/uc?export=download&id=1Oh-twIeXaJaJMwMcivFL3UOUweEa9Nlr",
                likesCount = 50,
                commentsCount = 5
            ),
        )
    }
}