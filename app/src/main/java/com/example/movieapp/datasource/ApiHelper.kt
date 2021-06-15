package com.example.movieapp.datasource

import javax.inject.Inject

class ApiHelper @Inject constructor(private val apiService: ApiService) {

    suspend fun getMovies() = apiService.getMovieList(
        "8ed700250305de124bef08dbb686472a",
        1
    )

}