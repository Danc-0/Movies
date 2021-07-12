package com.example.movieapp.api

import com.example.movieapp.model.Genre
import com.example.movieapp.model.GenreTV
import com.example.movieapp.model.MovieGenres
import com.example.movieapp.model.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    suspend fun getMovieList(@Query("api_key") api_key: String, @Query("page") page: Int) : MoviesResponse

    @GET("genre/movie/list")
    suspend fun getMovieGenreList(@Query("api_key") api_key: String, @Query("language") language: String) : MovieGenres

    @GET("genre/tv/list")
    suspend fun getTVListGenreList(@Query("api_key") api_key: String, @Query("language") language: String) : MovieGenres


}