package android.project.assignmentweek5.ui.users.viewmodel

import android.project.assignmentweek5.data.remote.api.dto.users.UsersDTOItem
import android.project.assignmentweek5.data.repository.users.UsersRepository
import android.project.assignmentweek5.utilities.Resources
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val userRepository: UsersRepository
) : ViewModel() {

    private val _usersState: MutableStateFlow<Resources<List<UsersDTOItem>>?> = MutableStateFlow(null)
    val usersState: StateFlow<Resources<List<UsersDTOItem>>?> = _usersState

    init {
        getUsers()
    }

    private fun getUsers() {
        viewModelScope.launch {
            userRepository.getUsers().collect { users  ->
                _usersState.value = users
            }
        }
    }

}