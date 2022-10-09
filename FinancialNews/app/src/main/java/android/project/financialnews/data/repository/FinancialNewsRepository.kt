package android.project.financialnews.data.repository

import android.project.financialnews.data.remote.dto.FinancialNewsDto
import android.project.financialnews.utilities.Resource
import kotlinx.coroutines.flow.Flow

interface FinancialNewsRepository {

     fun getAllFinancialNews(): Flow<Resource<FinancialNewsDto>>

}