package android.project.assignmentweek5.ui.posts.adapter

import android.project.assignmentweek5.data.remote.api.dto.posts.PostsDTOItem
import android.project.assignmentweek5.databinding.PostItemLayoutBinding
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class PostsAdapter(
    private val posts: List<PostsDTOItem>,
    private val listener: PostListener
) : RecyclerView.Adapter<PostsAdapter.PostsAdapterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsAdapterViewHolder {
       val binding: PostItemLayoutBinding = PostItemLayoutBinding.inflate(LayoutInflater
           .from(parent.context), parent, false)
        return PostsAdapterViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: PostsAdapterViewHolder, position: Int) {
        val post = posts[position]
        holder.bind(post)
    }

    override fun getItemCount(): Int {
      return posts.size
    }


    inner class PostsAdapterViewHolder(
        private val binding: PostItemLayoutBinding,
        private val listener: PostListener
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(dataHolder: PostsDTOItem) {
            binding.dataHolder = dataHolder
            itemView.setOnClickListener {
                listener.onClicked(dataHolder)
            }
            binding.executePendingBindings()
        }
    }
}

interface PostListener {
    fun onClicked(post: PostsDTOItem)
}