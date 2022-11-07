package android.project.assignmentweek5.ui.favorites.viewmodel

import android.project.assignmentweek5.data.local.database.entity.FavoritesEntity
import android.project.assignmentweek5.data.repository.posts.PostsRepository
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val repository: PostsRepository
) : ViewModel() {

    private val _favoritePostsState: MutableStateFlow<List<FavoritesEntity>?> = MutableStateFlow(null)
    val favoritePostsState: StateFlow<List<FavoritesEntity>?> = _favoritePostsState

    init {
        getFavoritePosts()
    }

    private fun getFavoritePosts() {
        viewModelScope.launch {
            repository.getFavoritePosts().collect{ posts ->
                _favoritePostsState.value = posts
            }
        }
    }

    fun deletePostFromFavorites(fav: FavoritesEntity) {
        viewModelScope.launch {
            repository.deleteItem(fav)
        }
    }


}