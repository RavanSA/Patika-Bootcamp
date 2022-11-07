package android.project.assignmentweek5.data.local.database.dao

import android.project.assignmentweek5.data.local.database.base.BaseDao
import android.project.assignmentweek5.data.local.database.entity.FavoritesEntity
import android.project.assignmentweek5.utilities.Constants
import androidx.room.*
import kotlinx.coroutines.flow.Flow


@Dao
interface FavoriteDao : BaseDao<FavoritesEntity> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addItemToFavorites(fav: FavoritesEntity)

    @Delete
    suspend fun deleteItem(fav: FavoritesEntity)

    @Query("SELECT * FROM ${Constants.TABLE_FAVORITES_NAME}")
    fun getFavoriteItems(): Flow<List<FavoritesEntity>>

    @Query("SELECT EXISTS (SELECT 1 FROM ${Constants.TABLE_FAVORITES_NAME} WHERE postId=:postId)")
    fun isItemAddedtoFavorites(postId: String): Flow<Int?>

}