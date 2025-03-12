package com.the_emperor_zurg.first_lab.ui.posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.the_emperor_zurg.first_lab.databinding.FragmentPostsBinding

class PostsFragment : Fragment() {

    private var _binding: FragmentPostsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPostsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val posts = listOf(
            Post("magnuskarlsen", "https://avatars.mds.yandex.net/i?id=4dac1b3a2ea8d1a91cf8200b769ea0a9_l-5232623-images-thumbs&amp;n=13", 7654),
            Post("maxverstappen", "https://betassist.obs.ru-moscow-1.hc.sbercloud.ru/uploads/news/image/0/0/266/133097/Screenshot_4-seo.png", 11233),
            Post("leomessi", "https://cdnn21.img.ria.ru/images/07e6/0c/13/1839620898_0:0:3072:1728_1920x1080_80_0_0_c39d82554e3d3b29f8df4a5eaed36895.jpg", 23345123),
        )

        binding.postsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        val adapter = PostsAdapter(posts, true)
        binding.postsRecyclerView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
