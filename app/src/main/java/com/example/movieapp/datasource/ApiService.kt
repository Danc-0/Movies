package com.example.movieapp.datasource

import com.example.movieapp.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiService {

    // Define Get request with query string parameter as page number
    @GET("movie/popular")
    suspend fun getMovieList(@Query("api_key") api_key: String, @Query("page") page: Int) : MovieResponse

}