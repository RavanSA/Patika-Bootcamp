package android.project.assignmentweek5.data.local.database.dao

import android.project.assignmentweek5.data.local.database.base.BaseDao
import android.project.assignmentweek5.data.local.database.entity.PostEntity
import android.project.assignmentweek5.utilities.Constants
import androidx.room.Dao
import androidx.room.Query

@Dao
interface PostDao : BaseDao<PostEntity> {
    @Query("SELECT * FROM ${Constants.TABLE_POST_NAME}")
    fun getAllPosts(): List<PostEntity>

    @Query("DELETE FROM ${Constants.TABLE_POST_NAME}")
    fun deleteAll()

    @Query("SELECT * FROM ${Constants.TABLE_POST_NAME} WHERE postId = :postId")
    fun getPostById(postId: String): PostEntity?

}