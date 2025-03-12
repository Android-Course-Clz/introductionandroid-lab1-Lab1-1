package ru.itmo.socialnetwork

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.itmo.socialnetwork.adapters.CommentAdapter
import ru.itmo.socialnetwork.models.Comment
import ru.itmo.socialnetwork.models.Post
import ru.itmo.socialnetwork.models.User

class CommentsBottomSheet(
    private val post: Post,
    private val user: User,
    private val onCommentAdded: () -> Unit
) : BottomSheetDialogFragment() {

    private lateinit var commentsRecyclerView: RecyclerView
    private lateinit var commentAdapter: CommentAdapter
    private lateinit var commentInput: EditText
    private lateinit var sendButton: ImageView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.comment_bottom_sheet, container, false)

        commentsRecyclerView = view.findViewById(R.id.commentsRecyclerView)
        commentInput = view.findViewById(R.id.commentInput)
        sendButton = view.findViewById(R.id.sendCommentButton)

        commentAdapter = CommentAdapter(post.comments)
        commentsRecyclerView.layoutManager = LinearLayoutManager(context)
        commentsRecyclerView.adapter = commentAdapter

        sendButton.setOnClickListener {
            val text = commentInput.text.toString().trim()
            if (text.isNotEmpty()) {
                val newComment = Comment(
                    id = post.comments.size + 1,
                    text = text,
                    user = user
                )
                post.comments.add(newComment)
                commentAdapter.notifyItemInserted(post.comments.size - 1)
                commentsRecyclerView.scrollToPosition(post.comments.size - 1)
                commentInput.text.clear()

                onCommentAdded()
            }
        }

        return view
    }

}
