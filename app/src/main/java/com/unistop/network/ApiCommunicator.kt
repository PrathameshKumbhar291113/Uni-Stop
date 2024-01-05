package com.unistop.network

import com.unistop.network.api_response.GetUniversityListResponse
import com.unistop.utils.RestConstantUniStop
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiCommunicator {
    @GET(RestConstantUniStop.GET_UNIVERSITIES)
    suspend fun getUniversityList(@Query ("country") countryName: String): Response<List<GetUniversityListResponse>>
}