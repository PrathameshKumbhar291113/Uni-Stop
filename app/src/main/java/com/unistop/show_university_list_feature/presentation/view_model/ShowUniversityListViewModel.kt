package com.unistop.show_university_list_feature.presentation.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unistop.network.api_response.GetUniversityListResponse
import com.unistop.show_university_list_feature.domain.use_case.GetUniversityUseCase
import com.unistop.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ShowUniversityListViewModel @Inject constructor(
    private val getUniversityUseCase: GetUniversityUseCase
): ViewModel(){
    private val _getUniversityList =
        MutableLiveData<NetworkResult<Response<List<GetUniversityListResponse>>>>()
    val getUniversityList: LiveData<NetworkResult<Response<List<GetUniversityListResponse>>>> get() = _getUniversityList

    var searchValue = MutableLiveData("")

    fun getUniversityList(countryName: String){
            getUniversityUseCase(countryName).onEach {
                when (it) {
                    is NetworkResult.Loading -> {}
                    is NetworkResult.Success -> {
                        _getUniversityList.postValue(it)
                    }
                    is NetworkResult.Error -> {}
                }
            }.launchIn(viewModelScope)
    }
}