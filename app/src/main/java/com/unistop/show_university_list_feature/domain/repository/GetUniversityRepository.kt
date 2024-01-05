package com.unistop.show_university_list_feature.domain.repository

import com.unistop.network.api_response.GetUniversityListResponse
import retrofit2.Response
import retrofit2.http.Query

interface GetUniversityRepository {
    suspend fun getUniversityList(countryName: String): Response<List<GetUniversityListResponse>>
}