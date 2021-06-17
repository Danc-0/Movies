package com.example.movieapp

import com.example.movieapp.api.ApiService
import com.example.movieapp.data.datasource.RemoteDatasource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object MyAppModule {

    @Singleton
    @Provides
    fun appModule(remoteDataSource: RemoteDatasource): ApiService {
        return remoteDataSource.buildRetrofit(ApiService::class.java)
    }

}