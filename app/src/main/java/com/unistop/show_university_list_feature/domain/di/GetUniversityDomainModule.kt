package com.unistop.show_university_list_feature.domain.di

import com.unistop.show_university_list_feature.domain.repository.GetUniversityRepository
import com.unistop.show_university_list_feature.domain.use_case.GetUniversityUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GetUniversityDomainModule {
    @Provides
    @Singleton
    fun getUniversities(
        getUniversityRepository: GetUniversityRepository
    ): GetUniversityUseCase {
        return GetUniversityUseCase(getUniversityRepository)
    }
}