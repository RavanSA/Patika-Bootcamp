package android.project.assignmentweek5.ui.favorites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.project.assignmentweek5.R
import android.project.assignmentweek5.data.local.database.entity.FavoritesEntity
import android.project.assignmentweek5.databinding.FragmentFavoritesBinding
import android.project.assignmentweek5.ui.favorites.adapter.FavoritesAdapter
import android.project.assignmentweek5.ui.favorites.adapter.FavoritesListener
import android.project.assignmentweek5.ui.favorites.viewmodel.FavoritesViewModel
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoritesFragment : Fragment(), FavoritesListener {

    private lateinit var navController: NavController
    private lateinit var binding: FragmentFavoritesBinding
    private val favoritesViewModel by viewModels<FavoritesViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        observeFavoritePosts()
    }

    private fun observeFavoritePosts() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                favoritesViewModel.favoritePostsState.collect { fav ->
                    binding.rvFavoritesList.adapter = fav?.let {
                        binding.tvEmptyList.visibility = View.INVISIBLE
                        FavoritesAdapter(it, this@FavoritesFragment)
                    }
                }
            }
        }
    }

    override fun onClicked(favorite: FavoritesEntity) {
        navController.navigate(R.id.action_favoritesFragment_to_postDetailFragment, Bundle().apply {
            putString("postId", favorite.postId.toString())
        })
    }

    override fun onFavoriteIconClicked(favorite: FavoritesEntity) {
        favoritesViewModel.deletePostFromFavorites(favorite)
    }

}