package com.example.movieapp.data.repository

import com.example.movieapp.data.datasource.ApiHelper
import javax.inject.Inject

class MovieRepository @Inject constructor(private val apiHelper: ApiHelper): BaseRepository() {

    suspend fun getMovies() =
        safeApiCall {
            apiHelper.getMovies()
        }
}