package com.example.movieapp.repository

import com.example.movieapp.datasource.ApiHelper
import javax.inject.Inject

class MovieRepository @Inject constructor(private val apiHelper: ApiHelper): BaseRepository() {

    suspend fun getMovies() =
        safeApiCall {
            apiHelper.getMovies()
        }
}