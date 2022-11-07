package android.project.assignmentweek5.data.repository.posts

import android.project.assignmentweek5.data.local.database.AppDatabase
import android.project.assignmentweek5.data.local.database.entity.FavoritesEntity
import android.project.assignmentweek5.data.remote.api.endpoints.PostsAPI
import android.project.assignmentweek5.data.remote.api.dto.posts.PostsDTOItem
import android.project.assignmentweek5.utilities.Resources
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PostsRepositoryImpl @Inject constructor(
    private val api: PostsAPI,
    private val appDatabase: AppDatabase
) : PostsRepository {

    val favoritesDao = appDatabase.favoritesDao()

    override fun getPosts(): Flow<Resources<List<PostsDTOItem>>> = flow {
        emit(Resources.Loading<List<PostsDTOItem>>(true))
        val posts = api.getPosts()
        emit(Resources.Success<List<PostsDTOItem>>(data = posts))
    }.catch { error ->
        emit(Resources.Error<List<PostsDTOItem>>("Error Occurred $error"))
    }.flowOn(Dispatchers.IO)


    override fun getPostById(postId: String): Flow<Resources<PostsDTOItem>>
        = flow {
            emit(Resources.Loading<PostsDTOItem>(true))
            val post = api.getPostById(postId)
            emit(Resources.Success<PostsDTOItem>(data = post))
        }.catch { error ->
            emit(Resources.Error<PostsDTOItem>("Error Occurred $error"))
        }.flowOn(Dispatchers.IO)


    override suspend fun addItemToFavorites(fav: FavoritesEntity) {
        return favoritesDao.addItemToFavorites(fav)
    }

    override suspend fun deleteItem(fav: FavoritesEntity) {
        return favoritesDao.deleteItem(fav)
    }

    override fun getFavoriteItems(): Flow<List<FavoritesEntity>> {
        return favoritesDao.getFavoriteItems()
    }

    override fun isItemAddedtoFavorites(postId: String): Flow<Int?> {
        return favoritesDao.isItemAddedtoFavorites(postId)
    }


    override fun getFavoritePosts(): Flow<List<FavoritesEntity>> {
        return favoritesDao.getFavoriteItems()
    }
}
