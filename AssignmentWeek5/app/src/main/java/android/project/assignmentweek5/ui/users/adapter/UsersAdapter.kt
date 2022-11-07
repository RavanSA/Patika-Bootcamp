package android.project.assignmentweek5.ui.users.adapter

import android.project.assignmentweek5.data.remote.api.dto.posts.PostsDTOItem
import android.project.assignmentweek5.data.remote.api.dto.users.UsersDTOItem
import android.project.assignmentweek5.databinding.PostItemLayoutBinding
import android.project.assignmentweek5.databinding.UserItemLayoutBinding
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class UsersAdapter(
    private val users: List<UsersDTOItem>
) : RecyclerView.Adapter<UsersAdapter.UsersAdapterViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersAdapterViewHolder {
        val binding: UserItemLayoutBinding = UserItemLayoutBinding.inflate(LayoutInflater
            .from(parent.context), parent, false)
        return UsersAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UsersAdapterViewHolder, position: Int) {
        val user = users[position]
        holder.bind(user)
    }

    override fun getItemCount(): Int {
        return users.size
    }


    inner class UsersAdapterViewHolder(
        private val binding: UserItemLayoutBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(users: UsersDTOItem) {
            binding.users = users
            binding.executePendingBindings()
        }
    }

}
