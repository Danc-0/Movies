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

    suspend fun getTVGenre() = apiService.getTVListGenreList(
        "8ed700250305de124bef08dbb686472a",
        "en-US"
    )

    suspend fun getMovieRecommendations(movieID: Int, pageNo: Int) = apiService.getRecommendedMovieList(
        movieID,
        "8ed700250305de124bef08dbb686472a",
        "en-US",
        pageNo
    )

    suspend fun getLanguageList() = apiService.getLanguageList(
        "8ed700250305de124bef08dbb686472a"
    )

    suspend fun getMoviesByGenre(genre_id: Int) = apiService.getMoviesListByGenre(
        "8ed700250305de124bef08dbb686472a",
        genre_id
    )
}