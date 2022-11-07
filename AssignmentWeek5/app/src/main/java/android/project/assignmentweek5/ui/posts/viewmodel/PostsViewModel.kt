package android.project.assignmentweek5.ui.posts.viewmodel

import android.project.assignmentweek5.data.remote.api.dto.posts.PostsDTO
import android.project.assignmentweek5.data.remote.api.dto.posts.PostsDTOItem
import android.project.assignmentweek5.data.repository.posts.PostsRepository
import android.project.assignmentweek5.utilities.Resources
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val postsRepository: PostsRepository
) : ViewModel() {

    private val _postsState: MutableStateFlow<Resources<List<PostsDTOItem>>?> = MutableStateFlow(null)
    val postsState: StateFlow<Resources<List<PostsDTOItem>>?> = _postsState

    init {
        getPosts()
    }

    private fun getPosts() {
        viewModelScope.launch {
          postsRepository.getPosts().collect { posts ->
             _postsState.value = posts
          }
        }
    }

}