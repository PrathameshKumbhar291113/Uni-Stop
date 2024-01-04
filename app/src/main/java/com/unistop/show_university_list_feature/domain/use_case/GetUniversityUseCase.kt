package com.unistop.show_university_list_feature.domain.use_case

import com.unistop.network.api_response.GetUniversityList
import com.unistop.show_university_list_feature.domain.repository.GetUniversityRepository
import com.unistop.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class GetUniversityUseCase @Inject constructor(private val getUniversityRepository: GetUniversityRepository) {
    operator fun invoke(countryName: String) = flow<NetworkResult<Response<List<GetUniversityList>>>> {
        emit(NetworkResult.Loading())
        emit(NetworkResult.Success(data = getUniversityRepository.getUniversityList(countryName = countryName)))
    }.catch {
        emit(NetworkResult.Error(it.message.toString()))
    }.flowOn(Dispatchers.IO)
}