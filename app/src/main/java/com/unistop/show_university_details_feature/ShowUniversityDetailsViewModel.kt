package com.unistop.show_university_details_feature

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.unistop.network.api_response.GetUniversityListResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ShowUniversityDetailsViewModel @Inject constructor(): ViewModel() {

    private val _universityDetails = MutableLiveData<GetUniversityListResponse>()
    val universityDetails : LiveData<GetUniversityListResponse> = _universityDetails

    fun setUniversityDetails(universityDetails: GetUniversityListResponse){
        _universityDetails.value = universityDetails
    }
}