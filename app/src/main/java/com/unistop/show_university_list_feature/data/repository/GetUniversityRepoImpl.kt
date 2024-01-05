package com.unistop.show_university_list_feature.data.repository

import com.unistop.network.ApiCommunicator
import com.unistop.network.api_response.GetUniversityListResponse
import com.unistop.show_university_list_feature.domain.repository.GetUniversityRepository
import dagger.Provides
import retrofit2.Response
import javax.inject.Inject


class GetUniversityRepoImpl @Inject constructor(private val apiCommunicator: ApiCommunicator) :
    GetUniversityRepository {
    override suspend fun getUniversityList(countryName: String): Response<List<GetUniversityListResponse>> {
        return apiCommunicator.getUniversityList(countryName)
    }
}