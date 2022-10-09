package android.project.financialnews.data.remote.dto

import android.os.Parcelable
import com.google.gson.Gson
import kotlinx.parcelize.Parcelize

@Parcelize
data class Article(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String
) : Parcelable {
    fun toJson() : String {
        return Gson().toJson(this)
    }

    companion object {
        fun fromJson(json: String) : Article {
            return Gson().fromJson(json, Article::class.java)
        }
    }
}