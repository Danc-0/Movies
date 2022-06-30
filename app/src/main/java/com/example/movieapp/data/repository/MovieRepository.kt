package com.example.movieapp.data.repository

import android.util.Log
import com.example.movieapp.BuildConfig
import com.example.movieapp.api.ApiService
import com.example.movieapp.data.datasource.ApiHelper
import com.example.movieapp.model.MoviesResponse
import retrofit2.http.Query
import javax.inject.Inject

class MovieRepository @Inject constructor(private val apiService: ApiService) : BaseRepository() {

    private val apiKey: String = BuildConfig.API_KEY

    suspend fun getMovies(nextPage: Int) =
        safeApiCall {
            apiService.getMovieList(apiKey, nextPage)
        }

    suspend fun getMovieGenre(language: String) =
        safeApiCall {
            apiService.getMovieGenreList(apiKey, language)
        }

    suspend fun getTVGenre(language: String) =
        safeApiCall {
            apiService.getTVListGenreList(apiKey, language)
        }

    suspend fun getRecommendedMovies(movieID: Int, pageNo: Int,
    language: String) =
        safeApiCall {
            apiService.getRecommendedMovieList(movieID, apiKey, language, pageNo)
        }

    suspend fun getLanguageList() =
        safeApiCall {
            apiService.getLanguageList(apiKey)
        }

    suspend fun getMoviesByGenre(genre_id: Int) =
        safeApiCall {
            apiService.getMoviesListByGenre(apiKey, genre_id)
        }

}