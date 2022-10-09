package android.project.financialnews.data.repository

import android.project.financialnews.data.remote.FinancialNewsAPI
import android.project.financialnews.data.remote.dto.FinancialNewsDto
import android.project.financialnews.utilities.Resource
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FinancialNewsRepositoryImpl @Inject constructor(
    private val api: FinancialNewsAPI
) : FinancialNewsRepository {

    override fun getAllFinancialNews(
    ): Flow<Resource<FinancialNewsDto>> = flow {
            emit(Resource.Loading<FinancialNewsDto>(true))
            val financialNewsList = api.getNewsList()
            emit(Resource.Success<FinancialNewsDto>(data = financialNewsList))
            }.catch { emit(Resource.Error<FinancialNewsDto>("Error Occurred"))}
            .flowOn(Dispatchers.IO)

}