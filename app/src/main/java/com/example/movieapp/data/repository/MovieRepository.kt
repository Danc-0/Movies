package com.example.movieapp.data.repository

import com.example.movieapp.data.datasource.ApiHelper
import javax.inject.Inject

class MovieRepository @Inject constructor(private val apiHelper: ApiHelper): BaseRepository() {

    suspend fun getMovies() =
        safeApiCall {
            apiHelper.getMovies()
        }

    suspend fun getMovieGenre() =
        safeApiCall {
            apiHelper.getMoviesGenre()
        }

    suspend fun getTVGenre() =
        safeApiCall {
            apiHelper.getTVGenre()
        }

    suspend fun getRecommendedMovies(movieID: Int, pageNo: Int) =
        safeApiCall {
            apiHelper.getMovieRecommendations(movieID, pageNo)
        }

    suspend fun getLanguageList() =
        safeApiCall {
            apiHelper.getLanguageList()
        }

    suspend fun getMoviesByGenre(genre_id: Int) =
        safeApiCall {
            apiHelper.getMoviesByGenre(genre_id)
        }

}