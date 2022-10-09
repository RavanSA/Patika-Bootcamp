package android.project.financialnews.data.remote

import android.project.financialnews.data.remote.dto.FinancialNewsDto
import android.project.financialnews.utilities.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface FinancialNewsAPI {


    @GET("v2/everything")
    suspend fun getNewsList(
        @Query("q") exchange: String = "financial",
        @Query("apiKey") apiKey: String = Constants.API_KEY
    ): FinancialNewsDto

}