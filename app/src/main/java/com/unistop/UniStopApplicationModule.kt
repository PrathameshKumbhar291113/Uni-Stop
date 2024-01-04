package com.unistop

import com.unistop.network.ApiCommunicator
import com.unistop.utils.RestConstantUniStop
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UniStopApplicationModule {

        @Provides
        @Singleton
        fun provideApiCommunicator(): ApiCommunicator {
            return Retrofit.Builder()
                .baseUrl(RestConstantUniStop.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiCommunicator::class.java)

    }
}