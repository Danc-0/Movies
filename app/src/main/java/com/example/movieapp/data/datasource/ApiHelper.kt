package com.example.movieapp.data.datasource

import com.example.movieapp.api.ApiService
import javax.inject.Inject

class ApiHelper @Inject constructor(private val apiService: ApiService) {

    suspend fun getMovies() = apiService.getMovieList(
        "8ed700250305de124bef08dbb686472a",
        1
    )

    suspend fun getMoviesGenre() = apiService.getMovieGenreList(
        "8ed700250305de124bef08dbb686472a",
        "en-US"
    )

}