package ru.aroize.lab_1

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.aroize.lab_1.R.drawable

class Adapter(private var list: List<Item>) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    fun updateData(newData: List<Item>) {
        val diffCallback = ItemDiffCallback(list, newData)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        list = newData
        diffResult.dispatchUpdatesTo(this)
    }

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.posts_list, parent, false)

        return ViewHolder(view)
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = list[position]

        Glide.with(holder.postProfilePictureView)
            .load(item.profilePicture)
            .placeholder(drawable.comment_button)
            .error(drawable.like_button_pressed)
            .into(holder.postProfilePictureView)

        // sets the image to the imageview from our itemHolder class
        holder.postImageView.setImageResource(item.postImage)

        // sets the text to the textview from our itemHolder class
        holder.postTitleView.text = item.title

        holder.postLikeButtonView.setOnClickListener{
            holder.postLikeButtonView.setImageResource(drawable.like_button_pressed)
            var numOfLikesR = holder.postAmountOfLikesView.text
            var numOfLikes = numOfLikesR.toString().toInt()
            numOfLikes++
            holder.postAmountOfLikesView.text = numOfLikes.toString()
        }

        holder.postCommentFieldView.visibility = View.GONE

        holder.postCommentButtonView.setOnClickListener{
            if (holder.postCommentFieldView.visibility == View.GONE) {
                holder.postCommentFieldView.visibility = View.VISIBLE
            } else {
                holder.postCommentFieldView.visibility = View.GONE
                holder.postAmountOfCommentsView.text = ("1").toString()
            }
        }

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return list.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val postProfilePictureView: ImageView = itemView.findViewById(R.id.profilePicture)
        val postImageView: ImageView = itemView.findViewById(R.id.postPicture)
        val postTitleView: TextView = itemView.findViewById(R.id.postTitle)
        val postLikeButtonView: ImageView = itemView.findViewById(R.id.likeButton)
        val postAmountOfLikesView: TextView = itemView.findViewById(R.id.amountOfLikes)
        val postCommentButtonView: AppCompatImageView = itemView.findViewById(R.id.commentButton)
        val postAmountOfCommentsView: TextView = itemView.findViewById(R.id.amountOfComments)
        val postCommentFieldView: EditText = itemView.findViewById(R.id.commentField)
    }
}