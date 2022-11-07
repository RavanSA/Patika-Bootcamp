package android.project.assignmentweek5.data.remote.api.dto.posts

import android.project.assignmentweek5.data.local.database.entity.FavoritesEntity
import android.project.assignmentweek5.ui.favorites.FavoritesFragment

data class PostsDTOItem(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int
)

fun PostsDTOItem.toFavorites() : FavoritesEntity {
    return FavoritesEntity(
        postId = id,
        postTitle = title,
        postBody = body
    )
}