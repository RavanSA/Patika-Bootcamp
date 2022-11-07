package android.project.assignmentweek5.data.remote.api.endpoints

import android.project.assignmentweek5.data.remote.api.dto.posts.PostsDTOItem
import retrofit2.http.GET
import retrofit2.http.Path

interface PostsAPI {

    @GET("posts")
    suspend fun getPosts(): List<PostsDTOItem>

    @GET("posts/{id}")
    suspend fun getPostById(@Path("id") id: String) : PostsDTOItem

}