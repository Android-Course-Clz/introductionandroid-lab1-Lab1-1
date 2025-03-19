package ru.aroize.lab_1

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.aroize.lab_1.databinding.ItemPostBinding

class Adapter(private var list: List<Item>) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    fun updateData(newData: List<Item>) {
        val diffCallback = ItemDiffCallback(list, newData)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        list = newData
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(private val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Item) {
            Glide.with(binding.root)
                .load(item.profilePicture)
                .placeholder(R.drawable.comment_button)
                .error(R.drawable.like_button_pressed)
                .into(binding.profilePicture)

            binding.postPicture.setImageResource(item.postImage)

            binding.postTitle.text = item.title

            binding.amountOfLikes.text = item.amountOfLikes.toString()

            if(item.likePressed) {
                binding.likeButton.setImageResource(R.drawable.like_button_pressed)
            } else {
                binding.likeButton.setImageResource(R.drawable.like_button)
            }
            binding.likeButton.setOnClickListener{
                if(item.likePressed) {
                    binding.likeButton.setImageResource(R.drawable.like_button)
                    item.amountOfLikes--
                    binding.amountOfLikes.text = item.amountOfLikes.toString()
                    item.likePressed = false
                } else {
                    binding.likeButton.setImageResource(R.drawable.like_button_pressed)
                    item.amountOfLikes++
                    binding.amountOfLikes.text = item.amountOfLikes.toString()
                    item.likePressed = true
                }
            }

            binding.amountOfComments.text = item.amountOfComments.toString()
            binding.commentField.setText(item.commentEdit)
            binding.commentField.visibility = View.GONE

            binding.commentButton.setOnClickListener{
                if (binding.commentField.visibility == View.GONE) {
                    binding.commentField.visibility = View.VISIBLE
                } else {
                    binding.commentField.visibility = View.GONE
                    item.amountOfComments = 1
                    binding.amountOfComments.text = "1"
                    item.commentEdit = binding.commentField.toString()
                }
            }
        }
    }
}