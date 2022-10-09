package android.project.financialnews.data.remote.dto

data class FinancialNewsDto(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)