package android.project.assignmentweek5.data.repository.users

import android.project.assignmentweek5.data.remote.api.dto.users.UsersDTOItem
import android.project.assignmentweek5.utilities.Resources
import kotlinx.coroutines.flow.Flow

interface UsersRepository {

    fun getUsers(): Flow<Resources<List<UsersDTOItem>>>

}