package android.project.assignmentweek5.ui.posts.postdetail

import android.project.assignmentweek5.data.local.database.entity.FavoritesEntity
import android.project.assignmentweek5.data.remote.api.dto.posts.PostsDTOItem
import android.project.assignmentweek5.data.remote.api.dto.posts.toFavorites
import android.project.assignmentweek5.data.repository.posts.PostsRepository
import android.project.assignmentweek5.utilities.Resources
import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PostDetailViewModel @Inject constructor(
    private val postsRepository: PostsRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _postDetailState: MutableStateFlow<Resources<PostsDTOItem>?> = MutableStateFlow(null)
    val postDetailState: StateFlow<Resources<PostsDTOItem>?> = _postDetailState

    private val _isAdded: MutableStateFlow<Boolean?> = MutableStateFlow(null)
    val isAdded: StateFlow<Boolean?> = _isAdded


    init {
        savedStateHandle.get<String>("postId")?.let { postId ->
            getPostById(postId)
            isItemAddedToFavorites(postId)
        }
    }

    private fun getPostById(postId: String) {
        viewModelScope.launch {
            postsRepository.getPostById(postId).collect { post ->
                _postDetailState.value = post
            }
        }
    }


    private fun addToFavorites(fav: FavoritesEntity) {
        viewModelScope.launch {
            postsRepository.addItemToFavorites(fav)
        }
    }

    private fun deleteItemFromFavorites(fav: FavoritesEntity) {
        viewModelScope.launch {
            postsRepository.deleteItem(fav)
        }
    }

    private fun isItemAddedToFavorites(postId: String) {
            postsRepository.isItemAddedtoFavorites(postId).onEach {
                val isAddedFavorites = it != 0
                _isAdded.value = isAddedFavorites
            }.launchIn(viewModelScope)
    }


     fun isFavoriteButtonClicked() {
        val data = _postDetailState.value?.data
        val fav = data?.toFavorites()
        if(_isAdded.value == true) fav?.let { deleteItemFromFavorites(it) }
        else fav?.let { addToFavorites(it) }
    }


}