package android.project.assignmentweek5.data.local.database.entity

import android.project.assignmentweek5.utilities.Constants
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = Constants.TABLE_FAVORITES_NAME
)
data class FavoritesEntity(
    @PrimaryKey
    @ColumnInfo(name = "postId")
    val postId: Int,
    @ColumnInfo(name = "postTitle")
    val postTitle: String?,
    @ColumnInfo(name = "postBody")
    val postBody: String?
)