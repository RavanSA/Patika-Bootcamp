package android.project.assignmentweek5.data.remote.api.endpoints

import android.project.assignmentweek5.data.remote.api.dto.users.UsersDTOItem
import retrofit2.http.GET

interface UsersAPI {

    @GET("users")
    suspend fun getUsers() : List<UsersDTOItem>

}