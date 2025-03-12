package com.the_emperor_zurg.first_lab.ui.profile

import android.os.Bundle
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.the_emperor_zurg.first_lab.ui.posts.PostsAdapter
import com.the_emperor_zurg.first_lab.databinding.FragmentProfileBinding
import com.the_emperor_zurg.first_lab.ui.posts.Post

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val profileViewModel =
            ViewModelProvider(this).get(ProfileViewModel::class.java)

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(this)
            .load("https://www.desktopbackground.org/download/3840x2400/2014/06/20/780834_other-wallpaper-crystal-chess-wallpapers-hd-hd-backgrounds_4000x3000_h.jpg")
            .into(binding.coverImage)

        Glide.with(this)
            .load("https://news.store.rambler.ru/img/03f1e06e245873f885cc3f63774e367e?img-format=auto&img-1-resize=height:730")
            .circleCrop()
            .into(binding.profileImage)

        binding.statusBlock.setOnClickListener {
            TransitionManager.beginDelayedTransition(binding.root)
            binding.detailedInfo.visibility = if (binding.detailedInfo.visibility == View.VISIBLE) {
                View.GONE
            } else {
                View.VISIBLE
            }
        }

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val posts = listOf(
            Post(
                "volodarmurzin",
                "https://photobooth.cdn.sports.ru/preset/message/2/4f/870ce4de540698e1e4ec306968429.jpeg?f=webp&amp;q=90&amp;s=2x&amp;w=730",
                7777),
            Post(
                "volodarmurzin",
                "https://photobooth.cdn.sports.ru/preset/message/2/4f/870ce4de540698e1e4ec306968429.jpeg?f=webp&amp;q=90&amp;s=2x&amp;w=730",
                7777)
        )

        binding.postsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.postsRecyclerView.adapter = PostsAdapter(posts, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}