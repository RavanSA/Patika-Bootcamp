package android.project.financialnews.viewmodels

import android.project.financialnews.data.remote.dto.FinancialNewsDto
import android.project.financialnews.data.repository.FinancialNewsRepository
import android.project.financialnews.utilities.Resource
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FinancialNewsListViewModel @Inject constructor(
    private val repository: FinancialNewsRepository
) : ViewModel() {

    private val _newsList = MutableLiveData<Resource<FinancialNewsDto>>()
    val newsList: LiveData<Resource<FinancialNewsDto>> = _newsList

    init {
        getNewsList()
    }

     private fun getNewsList() {
        viewModelScope.launch {
            repository.getAllFinancialNews().collect {
                _newsList.value = it
            }
        }
     }

}