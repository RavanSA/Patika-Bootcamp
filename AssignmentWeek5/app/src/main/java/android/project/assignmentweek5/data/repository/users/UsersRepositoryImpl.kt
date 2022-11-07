package android.project.assignmentweek5.data.repository.users

import android.project.assignmentweek5.data.local.database.AppDatabase
import android.project.assignmentweek5.data.remote.api.dto.posts.PostsDTOItem
import android.project.assignmentweek5.data.remote.api.dto.users.UsersDTOItem
import android.project.assignmentweek5.data.remote.api.endpoints.UsersAPI
import android.project.assignmentweek5.utilities.Resources
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(
    private val api: UsersAPI,
    private val appDatabase: AppDatabase
) : UsersRepository {

    override fun getUsers(): Flow<Resources<List<UsersDTOItem>>> = flow {
        emit(Resources.Loading<List<UsersDTOItem>>(true))
        val users = api.getUsers()

        emit(Resources.Success<List<UsersDTOItem>>(data = users))
    }.catch { error ->
        emit(Resources.Error<List<UsersDTOItem>>("Error Occurred $error"))
    }.flowOn(Dispatchers.IO)


}