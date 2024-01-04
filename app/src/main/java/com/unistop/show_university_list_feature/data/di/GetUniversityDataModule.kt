package com.unistop.show_university_list_feature.data.di

import com.unistop.network.ApiCommunicator
import com.unistop.show_university_list_feature.data.repository.GetUniversityRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GetUniversityDataModule {
    @Provides
    @Singleton
    fun providesGetProductsRepository(apiCommunicator: ApiCommunicator): GetUniversityRepoImpl {
        return GetUniversityRepoImpl(apiCommunicator)
    }
}
