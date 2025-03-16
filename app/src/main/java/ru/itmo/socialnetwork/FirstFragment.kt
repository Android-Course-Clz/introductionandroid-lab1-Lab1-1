package ru.itmo.socialnetwork

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import ru.itmo.socialnetwork.adapters.ProfileAdapter
import ru.itmo.socialnetwork.databinding.FragmentFirstBinding
import ru.itmo.socialnetwork.models.Comment
import ru.itmo.socialnetwork.models.Post
import ru.itmo.socialnetwork.models.User

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    private lateinit var profileAdapter: ProfileAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userOguzok = User(
            username = "oguzok",
            postsCount = 11,
            followersCount = 260,
            followingsCount = 217,
            avatarUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS32ZrjLFzC4-nJWsiMnR89P6WGNwdBLeg63g&s"
        )

        val userChief = User(
            username = "VictorPetrovich",
            postsCount = 1,
            followersCount = 2000,
            followingsCount = 12,
            avatarUrl = "https://static.wikia.nocookie.net/drebedenboi/images/3/31/Victor_Barinov.jpg/revision/latest?cb=20240219104038&path-prefix=ru"
        )

        val userFedya = User(
            username = "fedya",
            postsCount = 21,
            followersCount = 512,
            followingsCount = 465,
            avatarUrl = "https://www.eg.ru/wp-content/uploads/2024/08/pochemu-zvezda-kuhni-mihail-tarabukin-obzavelsya-komnatoy-obid-i-chto-stoit-za-ego-strahom-pered-jenitboy.jpg"
        )

        val posts = listOf(
            Post(
                id = 1,
                imageUrl = "https://static.wikia.nocookie.net/faketvchannels/images/0/05/%D0%9E%D0%B3%D1%83%D0%B7%D0%BE%D0%BA_%D0%A2%D0%92_%D0%9B%D0%BE%D0%B3%D0%BE.webp/revision/latest?cb=20231228060848&path-prefix=ru",
                description = "chief)",
                likes = 150,
                comments = mutableListOf(
                    Comment(id = 1, text = "слыш огузок удали быстро!!", user = userChief)
                ),
                time = "22 ч. назад"
            ),
            Post(
                id = 2,
                imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcThxR4dv7zLEHu-Lg_y8vlPuSgqSu4lv0GOSQ&s",
                description = "minetXD",
                likes = 220,
                comments = mutableListOf(
                    Comment(id = 1, text = "хахахах Сеня красавчик", user = userFedya)
                ),
                time = "1 день назад"
            )
        )

        profileAdapter = ProfileAdapter(posts, userOguzok)
        binding.postsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.postsRecyclerView.adapter = profileAdapter
        binding.postsRecyclerView.setHasFixedSize(true)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
