package android.project.assignmentweek5.ui.favorites.adapter

import android.project.assignmentweek5.data.local.database.entity.FavoritesEntity
import android.project.assignmentweek5.data.remote.api.dto.posts.PostsDTOItem
import android.project.assignmentweek5.databinding.FavoritesItemLayoutBinding
import android.project.assignmentweek5.databinding.PostItemLayoutBinding
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class FavoritesAdapter(
    private val favorites: List<FavoritesEntity>,
    private val listener: FavoritesListener
) : RecyclerView.Adapter<FavoritesAdapter.FavoritesAdapterViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesAdapterViewHolder {
        val binding: FavoritesItemLayoutBinding = FavoritesItemLayoutBinding.inflate(LayoutInflater
            .from(parent.context), parent, false)
        return FavoritesAdapterViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: FavoritesAdapterViewHolder, position: Int) {
        val favorite = favorites[position]
        holder.bind(favorite)
    }

    override fun getItemCount(): Int {
        return favorites.size
    }


    inner class FavoritesAdapterViewHolder(
        private val binding: FavoritesItemLayoutBinding,
        private val listener: FavoritesListener
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(favorite: FavoritesEntity) {
            binding.favorite = favorite
            binding.ivFavoriteIcon.setOnClickListener {
                listener.onFavoriteIconClicked(favorite)
            }
            itemView.setOnClickListener {
                listener.onClicked(favorite)
            }
            binding.executePendingBindings()
        }
    }
}

interface FavoritesListener {
    fun onClicked(favorite: FavoritesEntity)
    fun onFavoriteIconClicked(favorite: FavoritesEntity)

}