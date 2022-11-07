package android.project.assignmentweek5.data.repository.posts


import android.project.assignmentweek5.data.local.database.entity.FavoritesEntity
import android.project.assignmentweek5.data.remote.api.dto.posts.PostsDTOItem
import android.project.assignmentweek5.utilities.Resources
import kotlinx.coroutines.flow.Flow

interface PostsRepository {

    fun getPosts() : Flow<Resources<List<PostsDTOItem>>>

    fun getPostById(postId: String) : Flow<Resources<PostsDTOItem>>

    suspend fun addItemToFavorites(fav: FavoritesEntity)

    suspend fun deleteItem(fav: FavoritesEntity)

    fun getFavoriteItems(): Flow<List<FavoritesEntity>>

    fun isItemAddedtoFavorites(postId: String): Flow<Int?>

    fun getFavoritePosts() : Flow<List<FavoritesEntity>>

}